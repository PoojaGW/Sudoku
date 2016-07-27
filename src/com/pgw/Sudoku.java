package com.pgw;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Generator code modified from https://arunabhghosal.wordpress.com/2015/04/26/generating-sudoku-puzzle/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.*;

class Sudoku {

    private static int [][] soln = new int [9][9];
    private static int[][] grid = new int[9][9];

    private static HashMap table = new HashMap();

    private int[][]game = new int[9][9];
    private int[][]solution = new int[9][9];
    private String id;
    //create id

    public Sudoku(int[][]g, int[][]s, String key){
        game = g;
        solution = s;
        id = key;



        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                System.out.print(game[i][j]+"\t");
            }
            System.out.println("");
        }
    }



    private static String randomString() {
        char[] characterSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[16];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }


    public static boolean check(String json, Sudoku sudoku) {
        return json.equals(solutionToJSON(sudoku));
    //fix at some point
    }
    //return json==toJSON(table.get(id).solution);}

    public static String solutionToJSON(Sudoku sudoku){

        String jsonGame = "{\"game\":[";
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {

                if(sudoku.solution[i][j]==0)
                    jsonGame+="[\""+(i+1)+"-"+(j+1)+"\",\"\"]";
                else
                    jsonGame+="[\""+(i+1)+"-"+(j+1)+"\",\""+sudoku.solution[i][j]+"\"]";
                if(i!=8||j!=8) {
                    jsonGame+=",";
                }
            }

        }
        jsonGame+="],\"id\":\""+sudoku.id+"\"}";
        System.out.println(jsonGame);
        return jsonGame;
    }





    public static String gameToJSON(Sudoku sudoku){

        String jsonGame = "{\"game\":[";
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {

                if(sudoku.game[i][j]==0)
                    jsonGame+="[\""+(i+1)+"-"+(j+1)+"\",\"\"]";
                else
                    jsonGame+="[\""+(i+1)+"-"+(j+1)+"\",\""+sudoku.game[i][j]+"\"]";
                if(i!=8||j!=8) {
                    jsonGame+=",";
                }
            }

        }
        jsonGame+="],\"id\":\""+sudoku.id+"\"}";
        System.out.println(jsonGame);
        return jsonGame;
    }


    public static Sudoku create(){
        soln = new int [9][9];
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int counter=1,k1,k2;
        generate();
        random_gen(1);
        random_gen(0);
        System.out.println();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                System.out.print(soln[i][j]+"\t");
            }
            System.out.println("");
        }


        for(int i = 0; i <9; i ++)
            System.arraycopy(soln[i], 0, grid[i], 0, 9);

        Random rand=new Random();
        int n[]={0,3,6};
        for(int i=0;i<2;i++)
        {
            k1=n[rand.nextInt(n.length)];
            do{
                k2=n[rand.nextInt(n.length)];
            }while(k1==k2);
            if(counter==1)
                row_change(k1,k2);
            else col_change(k1,k2);
            counter++;
        }

        //Striking out
        for(k1=0;k1<9;k1++)
        {
            for(k2=0;k2<9;k2++)
                strike_out(k1,k2);
        }
        System.out.println();

        String tempID = randomString();
        while(table.containsKey(tempID))
            tempID = randomString();


        System.out.println(tempID);
        Sudoku s = new Sudoku(grid, soln, tempID);

        table.put(tempID, s);

        return s;

    }

    private static void generate()
    {
        int k;
        int n=1;
        for(int i=0;i<9;i++)
        {
            k=n;
            for(int j=0;j<9;j++)
            {
                if(k<=9){
                    soln[i][j]=k;
                    k++;
                }else{
                    k=1;
                    soln[i][j]=k;
                    k++;
                }
            }
            n=k+3;
            if(k==10)
                n=4;
            if(n>9)
                n=(n%9)+1;
        }
    }

    private static void random_gen(int check){
        int k1,k2,max=2,min=0;
        Random r= new Random();
        for(int i=0;i<3;i++)
        {
//There are three groups.So we are using for loop three times.
            k1=r.nextInt(max-min+1)+min;
//This while is just to ensure k1 is not equal to k2.
            do{
                k2=r.nextInt(max-min+1)+min;
            }while(k1==k2);
            max+=3;min+=3;
//check is global variable.
//We are calling random_gen two time from the main func.
//Once it will be called for columns and once for rows.
            if(check==1)
//calling a function to interchange the selected rows.
                permutation_row(k1,k2);
            else if(check==0)
                permutation_col(k1,k2);
        }
    }


    //For row
    private static void permutation_row(int k1,int k2){
        int temp;//k1 and k2 are two rows that we are selecting to interchange.
        for(int j=0;j<9;j++)
        {
            temp= soln[k1][j];
            soln[k1][j]= soln[k2][j];
            soln[k2][j]=temp;
        }
    }

    private static void permutation_col(int k1,int k2){
        int temp;
        for(int j=0;j<9;j++)
        {
            temp= soln[j][k1];
            soln[j][k1]= soln[j][k2];
            soln[j][k2]=temp;
        }
    }

    public static void row_change(int k1,int k2)
    {
        int temp;
        for(int n=1;n<=3;n++)
        {
            for(int i=0;i<9;i++)
            {
                temp= grid[k1][i];
                grid[k1][i]= grid[k2][i];
                grid[k2][i]=temp;
            }
            k1++;
            k2++;
        }
    }
    public static void col_change(int k1,int k2)
    {
        int temp;
        for(int n=1;n<=3;n++)
        {
            for(int i=0;i<9;i++)
            {
                temp= grid[i][k1];
                grid[i][k1]= grid[i][k2];
                grid[i][k2]=temp;
            }
            k1++;
            k2++;
        }
    }


    private static void strike_out(int k1,int k2)
    {
        int row_from;
        int row_to;
        int col_from;
        int col_to;
        int i,j,b,c;
        int rem1,rem2;
        int flag;
        int count=9;
        for(i=1;i<=9;i++)
        { flag=1;
            for(j=0;j<9;j++)
            {
                if(j!=k2)
                {
                    if(i== grid[k1][j])
                    {
                        flag=0;
                        break;
                    }
                }
            }
            if(flag==1)
            {
                for(c=0;c<9;c++)
                {
                    if(c!=k1)
                    {
                        if(i== grid[c][k2])
                        {
                            flag=0;
                            break;
                        }
                    }
                }
            }
            if(flag==1)
            {
                rem1=k1%3; rem2=k2%3;
                row_from=k1-rem1;
                row_to=k1+(2-rem1);
                col_from=k2-rem2;
                col_to=k2+(2-rem2);
                for(c=row_from;c<=row_to;c++)
                {
                    for(b=col_from;b<=col_to;b++)
                    {
                        if(c!=k1 && b!=k2)
                        {
                            if(i== grid[c][b]) {
                                flag = 0;
                                break;
                            }
                        }
                    }
                }
            }
            if(flag==0)
                count--;
        }
        if(count==1)
        {
            grid[k1][k2]=0;
           // counter_num--;
        }
    }
}