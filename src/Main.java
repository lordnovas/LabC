import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;


/**
 * todo:
 * Create row and columns of squares with a 2d array
 * make it 10 x 10
 *
 */



public class Main  extends JPanel
{

    Square square = new Square();


    //Constructor
    public Main()
    {
        super();
        setBackground(Color.white);
        setPreferredSize(new Dimension(1000, 1000));



    }


    public Dimension getPreferredSize()
    {
        return new Dimension(1000,1000);
    }



    public ArrayList<Square[]> createGrid()
    {
        ArrayList<Square[]> grid = new ArrayList<Square[]>(10);

        for(int i=0;i<10;i++)
        {
            grid.add(square.createRowOfSqrs(i*100));
        }

        return grid;
    }


    public void paintComponent(Graphics pen)
    {
        super.paintComponent(pen);

        ArrayList<Square[]> s = recursiveGrid();

        for(Square[] row : s)
        {
            for(int i=0;i<10;i++)
            {
                pen.drawRect(row[i].getX(),
                                    row[i].getY(),
                                    row[i].getWidth(),
                                    row[i].getHeight());
            }
        }

        print("Size of 3 "+s.get(3)[0].getY(),true);
    }
    // DO YOU NEED A HELPER METHOD?

    public ArrayList<Square[]> recursiveGrid()
    {
        ArrayList<Square[]> grid = new ArrayList<Square[]>(10);

        int first =0;
        int last = 9;
        int yValue = 100;

        grid.add(square.createRowOfSqrs(100));
        grid = recursiveGridHelper(grid,yValue,first,last);
        return grid;
    }

    private ArrayList<Square[]> recursiveGridHelper(ArrayList<Square[]> grid, int yValue, int first, int last)
    {
        if(first==last)
        {
            grid.add(square.createRowOfSqrs(yValue*first));
            print("Value of size is "+grid.size(),true);

        }
        else
        {
            grid.add(square.createRowOfSqrs(yValue*(first)));
            return  recursiveGridHelper(grid,yValue, first+1 , last);
        }

        return grid;
    }

    public static void main(String[] args)
    {
        Main panel = new Main();

        JFrame frame = new JFrame("GUI Program");
        frame.getContentPane().add(panel);
        frame.setSize(1000,1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    public static void print(String str,boolean isNewLine)
    {
        if(isNewLine)
        {
            System.out.println(str);
        }else{
            System.out.print(str);
        }

    }

    class Square {

        private int xPos = 0;
        private int yPos = 0;
        private int width = 50;
        private int height = 50;
        private Random rand = new Random();


        public Square()
        {
        }


        public void setX(int xPos)
        {
            this.xPos = xPos;
        }

        public int getX(){
            return xPos;
        }

        public void setY(int yPos){
            this.yPos = yPos;
        }

        public int getY(){
            return yPos;
        }

        public int getWidth(){
            return width;
        }

        public int getHeight(){
            return height;
        }

        public Square[] createRowOfSqrs(int yValue)
        {
            Square[] s = new Square[10];

            for(int i=0;i<= s.length-1;i++)
            {
                s[i] = new Square();
                if(i==0)
                {
                    s[i].setX(90);
                    s[i].setY(yValue);
                }
                else
                {
                    s[i].setX(s[i-1].getX()+90);
                    s[i].setY(yValue);
                }
            }

            return s;
        }


        public int getRandomNum(int min, int max)
        {
            //Generate random int between min-max
            return min + rand.nextInt(Math.abs((max - min)) + 1);
        }

    }

}
