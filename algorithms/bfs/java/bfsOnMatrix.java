import java.util.*;
class bfsOnMatrix
{
    static void bfs(int[][] grid)  
    {
        ArrayList<int[]>queue=new ArrayList<int[]>(); // priority queue used for the bfs (get shortest path length in case of unwighted matrix)
        queue.add(new int[]{0,0});
        int dirx[]={0,0,1,-1};
        int diry[]={1,-1,0,0};
        int level=0;
        int n=grid.length;
        boolean visited[][]=new boolean[n][n];
        int shortestpath[][]=new int[n][n];
        shortestpath[0][0]=1;
        visited[0][0]=true;
        while(queue.size()!=0)
        {
            int top[]=queue.get(0);
            queue.remove(0);
            if(grid[top[0]][top[1]]==1)
            {

                System.out.println(top[0]+" "+top[1]);
                for(int i=0;i<4;i++)
                {
                    int x=top[0]+dirx[i];
                    int y=top[1]+diry[i];
                    if(x<=n-1 && y<=n-1 && x>=0 && y>=0 && !visited[x][y] )
                    {
                        visited[x][y]=true;
                        shortestpath[x][y]=shortestpath[top[0]][top[1]]+1;
                        queue.add(new int[]{x,y});
                    }
                }
            }
        }
        System.out.println(shortestpath[n-1][n-1]); 
    }
    static void getPath(int [][]grid)
    {
        PriorityQueue<int[]>queue=new PriorityQueue<int[]>((b,a)->(grid[a[0]][a[1]]-grid[b[0]][b[1]])); // priority queue used for the bfs (get shortest path length in case of unwighted matrix)
        queue.add(new int[]{0,0});
        int dirx[]={0,0,1,-1};
        int diry[]={1,-1,0,0};
        int level=0;
        int n=grid.length;
        boolean visited[][]=new boolean[n][n];
        int path[][]=new int[n][n];
        path[0][0]=1;
        visited[0][0]=true;
        while(queue.size()!=0)
        {
            int top[]=queue.poll();
            
            if(grid[top[0]][top[1]]==1 || grid[top[0]][top[1]]==0)
            {

               // System.out.println(top[0]+" "+top[1]);
                for(int i=0;i<4;i++)
                {
                    int x=top[0]+dirx[i];
                    int y=top[1]+diry[i];
                    if(x<=n-1 && y<=n-1 && x>=0 && y>=0 && !visited[x][y] )
                    {
                        visited[x][y]=true;
                        path[x][y]=path[top[0]][top[1]]+grid[x][y];
                        queue.add(new int[]{x,y});
                    }
                    else if(x<=n-1 && y<=n-1 && x>=0 && y>=0 && visited[x][y])
                    {
                        path[x][y]=Math.max(path[x][y], path[top[0]][top[1]]+grid[x][y]);
                    }
                }
            }
        }
        ArrayList<int[]>actualpath=new ArrayList<>();
        visited=new boolean[n][n];
        actualpath.add(new int[]{n-1,n-1});
        visited[n-1][n-1]=true;
        while(actualpath.get(0)[0]!=0 || actualpath.get(0)[1]!=0)
        {

            int top[]=actualpath.get(0);
            int max=0;
            int dir[]=new int[2];
            for(int i=0;i<4;i++)
            {
                int x=top[0]+dirx[i];
                int y=top[1]+diry[i];
                if(x<=n-1 && y<=n-1 && x>=0 && y>=0 && path[x][y]>max && !visited[x][y])
                {
                    max=path[x][y];
                    dir[0]=x;
                    dir[1]=y;
                }
            }
            visited[dir[0]][dir[1]]=true;
            actualpath.add(0,dir);
            System.out.println(actualpath.get(0)[0]+" "+actualpath.get(0)[1]);
        } 

    }
   
    public static void main(String args[])
    {
        int [][]M={{1,1,0},{1,1,0},{0,1,1}};

        bfs(M);
        int [][]M1={{1,1,1},{1,1,0},{-1,1,1}};
        getPath(M1);

    }
}