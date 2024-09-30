import java.util.ArrayDeque;
import java.util.Deque;

// LC 200
public class NumberOfIslands {

    /**
     * Apply a traversal algorithm and consider all connected nodes as part of a single island.
     * Do this for the entire grid.
     * <p>
     * Use a visited[m][n] or modify the input grid[r][c]='0' such that at the end the entire grid is a water body.
     * <p>
     * TC: O(m * n)
     * SC: O(m * n) + O(m * n)
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int islands = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1' && visited[r][c] == 0) {
                    islands++;
                    // traverse
                    // dfs(grid, visited, r, c);
                    bfs(grid, visited, r, c);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int[][] visited, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        visited[r][c] = 1;
        /*
         * Use a delta matrix to move in all 4 directions
         */
        int[] dirs = {0, 1, 0, -1, 0};
        for (int i = 1; i < dirs.length; i++) {
            int rr = r + dirs[i - 1];
            int cc = c + dirs[i];
            // out of bounds check
            if (rr < 0 || rr >= m || cc < 0 || cc >= n) {
                continue;
            }
            // move in all 4 dirs if it is a land
            if (grid[rr][cc] == '1' && visited[rr][cc] == 0) {
                dfs(grid, visited, rr, cc);
            }
        }
    }

    private void bfs(char[][] grid, int[][] visited, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(r, c));
        visited[r][c] = 1;
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int row = curr.row;
            int col = curr.col;
            for (int i = 1; i < dirs.length; i++) {
                int rr = row + dirs[i - 1];
                int cc = col + dirs[i];
                // out of bounds check
                if (rr < 0 || rr >= m || cc < 0 || cc >= n) {
                    continue;
                }
                // move in all 4 dirs if it is a land
                if (grid[rr][cc] == '1' && visited[rr][cc] == 0) {
                    visited[rr][cc] = 1;
                    queue.offer(new Pair(rr, cc));
                }
            }
        }
    }

    /**
     * TC: O(m * n)
     * SC: O(m * n)
     *
     * @param grid
     * @return
     */
    public int numIslands_2(char[][] grid) {
        {
            int islands = 0;
            int m = grid.length;
            int n = grid[0].length;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] == '1') {
                        islands++;
                        // traverse
                        //dfs_2(grid, r, c);
                        bfs_2(grid, r, c);
                    }
                }
            }
            return islands;
        }
    }

    private void dfs_2(char[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        for (int i = 1; i < dirs.length; i++) {
            int rr = r + dirs[i - 1];
            int cc = c + dirs[i];
            // out of bounds check
            if (rr < 0 || rr >= m || cc < 0 || cc >= n) {
                continue;
            }
            // move in all 4 dirs if it is a land
            if (grid[rr][cc] == '1') {
                grid[rr][cc] = '0';
                dfs_2(grid, rr, cc);
            }
        }
    }

    private void bfs_2(char[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(r, c));
        grid[r][c] = 1;
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int row = curr.row;
            int col = curr.col;
            for (int i = 1; i < dirs.length; i++) {
                int rr = row + dirs[i - 1];
                int cc = col + dirs[i];
                // out of bounds check
                if (rr < 0 || rr >= m || cc < 0 || cc >= n) {
                    continue;
                }
                // move in all 4 dirs if it is a land
                if (grid[rr][cc] == '1') {
                    grid[rr][cc] = 0;
                    queue.offer(new Pair(rr, cc));
                }
            }
        }
    }

    private class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
