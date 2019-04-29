import java.util.HashSet;
import java.util.LinkedList;

public class RagingRapids {

    public static void main (String[] args) {

        LinkedList<Rower> Rowers = new LinkedList<Rower>();

        //Create the rowers
        Rowers.add(new Rower.RowerBuilder('A').setLeftBack().setBack().setRightFront().build());
        Rowers.add(new Rower.RowerBuilder('B').setFront().setLeftBack().setBack().setRightFront().build());
        Rowers.add(new Rower.RowerBuilder('C').setFront().setLeftFront().setRightBack().build());
        Rowers.add(new Rower.RowerBuilder('D').setLeftBack().setRightFront().build());
        Rowers.add(new Rower.RowerBuilder('E').setLeftFront().setBack().setRightBack().build());
        Rowers.add(new Rower.RowerBuilder('F').setLeftBack().setRightBack().build());
        Rowers.add(new Rower.RowerBuilder('G').setFront().setLeftBack().setRightFront().build());
        Rowers.add(new Rower.RowerBuilder('H').setLeftBack().setBack().setRightBack().build());
        Rowers.add(new Rower.RowerBuilder('I').setFront().setLeftFront().setBack().setRightFront().build());
        Rowers.add(new Rower.RowerBuilder('J').setFront().setLeftBack().setBack().setRightBack().build());
        Rowers.add(new Rower.RowerBuilder('K').setFront().setLeftFront().setRightFront().build());
        Rowers.add(new Rower.RowerBuilder('L').setLeftFront().setRightFront().build());


        /*The raft holds 4x3 rowers (how do rowers in the middle row?)

        Since the raft has its own "edges" we will simulate those by using a (4+2)x(3+2) matrix
        with the "edges" being "phantom" rowers.

         */

        Rower[][] raft = new Rower[6][5];

        //Front of the raft. Only need to set the 'back'.
        raft[0][1] = new Rower.RowerBuilder('0').build();
        raft[0][2] = new Rower.RowerBuilder('1').build();
        raft[0][3] = new Rower.RowerBuilder('2').build();

        //Back of the raft. Only need to set the 'front'.
        raft[5][1] = new Rower.RowerBuilder('3').setFront().build();
        raft[5][2] = new Rower.RowerBuilder('4').setFront().build();
        raft[5][3] = new Rower.RowerBuilder('5').setFront().build();

        //Left of the raft. Only need to set the Right side
        raft[1][0] = new Rower.RowerBuilder('6').setRightBack().build();
        raft[2][0] = new Rower.RowerBuilder('7').setRightBack().build();
        raft[3][0] = new Rower.RowerBuilder('8').setRightBack().build();
        raft[4][0] = new Rower.RowerBuilder('9').setRightFront().build();

        //Right of the raft. Only need to set the Left side.
        raft[1][4] = new Rower.RowerBuilder('!').setLeftBack().build();
        raft[2][4] = new Rower.RowerBuilder('!').setLeftFront().build();
        raft[3][4] = new Rower.RowerBuilder('!').setLeftFront().build();
        raft[4][4] = new Rower.RowerBuilder('!').setLeftFront().build();

        System.out.println("Hello world!");
    }


    private boolean canSeat(Rower testRower, Rower[][] raft, int row, int col) {
        //Function to check if a rower can take a seat
        //Check if the rower is "compatible" with the rowers on all sides.

        //Check rower in front.
        if (raft [row-1][col] !=null)
            if (!(raft[row-1][col].isBack()^testRower.isFront()))
                return false;

        //Check rower in back.
        if (raft [row+1][col] !=null)
            if (!(raft[row+1][col].isFront()^testRower.isBack()))
                return false;

        //Check rower to the left
        if (raft [row][col-1] !=null) {
            if (!(raft[row][col - 1].isRightFront() ^ testRower.isLeftFront()))
                return false;

            if (!(raft[row][col - 1].isRightBack() ^ testRower.isLeftBack()))
                return false;
        }

        //Check rower to the right
        if (raft [row][col+1] !=null) {
            if (!(raft[row][col + 1].isLeftFront()) ^ testRower.isRightFront())
                return false;

            if (!(raft[row][col + 1].isLeftBack() ^ testRower.isRightBack()))
                return false;
        }

        //If all matches!
        return true;
    }

    private boolean sitRower( LinkedList<Rower> availableRowers,Rower[][] raft, int row, int col) {
        //Recursive function for finding the set of rower.

        //First lets check if we are sitting the last one.

        if ((row==4) && (col==3) && (availableRowers.size()>0))
        {
            if (canSeat(availableRowers.getFirst(), raft,  4,3)) {
                Rower last = availableRowers.pop();
                raft[4][3] = last;
                return true;
            }
            else
                return false;
        }
        else
        {


        }

    }
}