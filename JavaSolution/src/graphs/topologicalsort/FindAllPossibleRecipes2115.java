package graphs.topologicalsort;

import java.util.*;

/**
 * 
 You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".


比较典型的Topological sort，主要难点在理解问题后graph的构建，要把supplies当作0-indegree的节点加进去，同时ingredients和recipes各自也都要作为节点。
 */


public class FindAllPossibleRecipes2115 {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        // recipes -> [ingredients...]
        Map<String, List<String>> graph = new HashMap();
        Map<String, Integer> inDegree = new HashMap();
        // We need this set just to identify if a node is a recipe.
        Set<String> recipeSet = new HashSet<>();

        // Add supplies as 0-degree nodes to the graph
        for (String supply : supplies) {
            graph.put(supply, new ArrayList<>());
            inDegree.put(supply, 0);
        }

        // Initialize recipes as nodes to the graph
        for (String recipe : recipes) {
            recipeSet.add(recipe);
            graph.put(recipe, new ArrayList<>());
            inDegree.put(recipe, 0);
        }
        // Initilize ingredients as nodes, and build edges to recipes
        for (int i = 0; i < ingredients.size(); i++) {
            String recipe = recipes[i];
            for (String ingredient : ingredients.get(i)) {
                graph.putIfAbsent(ingredient, new ArrayList<>());
                graph.get(ingredient).add(recipe);
                inDegree.put(recipe, inDegree.get(recipe) + 1);
            }
        }

        // Topological sort
        Deque<String> deque = new ArrayDeque<>();

        for (String node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                deque.addLast(node);
            }
        }

        List<String> recipesResult = new ArrayList<>();
        while (deque.size() > 0) {
            String node = deque.removeFirst();
            if (recipeSet.contains(node)) {
                recipesResult.add(node);
            }

            for (String adjcentNode : graph.get(node)) {
                inDegree.put(adjcentNode, inDegree.get(adjcentNode) - 1);
                if (inDegree.get(adjcentNode) == 0) {
                    deque.addLast(adjcentNode);
                }
            }
        }

        return recipesResult;
    }
}
