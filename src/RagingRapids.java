import java.util.ArrayList;

public class RagingRapids {


    public static void main(String[] args) {

        ArrayList<Rower> rowers = new ArrayList<>();

        loadRowers(rowers);


        /*The raft holds 4x3 rowers (how do rowers in the middle row?)

        Since the raft has its own "edges" we will simulate those by using a (4+2)x(3+2) matrix
        with the "edges" being "phantom" rowers.
         */

        Rower[][] raft = new Rower[6][5];

        defineRaft_ropeFront(raft);


        //Lets see which rowers could sit on the first seat:
        /*rowers.stream()
                .filter(r -> canSeat(r, raft, 1, 1))
                .forEach(System.out::println);
        */


        if (seatNext(rowers, raft, 1, 1,true)) {
            System.out.println("Solution Found! - Rope Front");
            drawRaft(raft);

        } else
            System.out.println("Couldn't solve it.");

        //Lets flip the raft.
        defineRaft_ropeBack (raft);

        //restart the rowers
        loadRowers(rowers);

        if (seatNext(rowers, raft, 1, 1)) {
            System.out.println("Solution Found! - Rope Back");
            drawRaft(raft);

        } else
            System.out.println("Couldn't solve it.");

    }

    private static void defineRaft_ropeFront(Rower[][] raft) {
        //Front of the raft. Only need to set the 'back'.
        raft[0][1] = new Rower.RowerBuilder("F1").build();
        raft[0][2] = new Rower.RowerBuilder("F2").build();
        raft[0][3] = new Rower.RowerBuilder("F3").build();

        //Back of the raft. Only need to set the 'front'.
        raft[5][1] = new Rower.RowerBuilder("B1").setFront().build();
        raft[5][2] = new Rower.RowerBuilder("B2").setFront().build();
        raft[5][3] = new Rower.RowerBuilder("B3").setFront().build();

        //Left of the raft. Only need to set the Right side
        raft[1][0] = new Rower.RowerBuilder("L1").setRightBack().build();
        raft[2][0] = new Rower.RowerBuilder("L2").setRightBack().build();
        raft[3][0] = new Rower.RowerBuilder("L3").setRightBack().build();
        raft[4][0] = new Rower.RowerBuilder("L4").setRightFront().build();

        //Right of the raft. Only need to set the Left side.
        raft[1][4] = new Rower.RowerBuilder("R1").setLeftBack().build();
        raft[2][4] = new Rower.RowerBuilder("R2").setLeftFront().build();
        raft[3][4] = new Rower.RowerBuilder("R3").setLeftFront().build();
        raft[4][4] = new Rower.RowerBuilder("R4").setLeftFront().build();

        // just to be safe null the seats
        for (int r = 1; r<=4; r++)
            for (int c=1; c<=3; c++)
                raft[r][c]=null;
    }

    private static void defineRaft_ropeBack(Rower[][] raft) {
        //Front of the raft. Only need to set the 'back'.
        raft[0][1] = new Rower.RowerBuilder("F1").setBack().build();
        raft[0][2] = new Rower.RowerBuilder("F2").setBack().build();
        raft[0][3] = new Rower.RowerBuilder("F3").setBack().build();

        //Back of the raft. Only need to set the 'front'.
        raft[5][1] = new Rower.RowerBuilder("B1").build();
        raft[5][2] = new Rower.RowerBuilder("B2").build();
        raft[5][3] = new Rower.RowerBuilder("B3").build();

        //Left of the raft. Only need to set the Right side
        raft[1][0] = new Rower.RowerBuilder("L1").setRightBack().build();
        raft[2][0] = new Rower.RowerBuilder("L2").setRightBack().build();
        raft[3][0] = new Rower.RowerBuilder("L3").setRightBack().build();
        raft[4][0] = new Rower.RowerBuilder("L4").setRightFront().build();

        //Right of the raft. Only need to set the Left side.
        raft[1][4] = new Rower.RowerBuilder("R1").setLeftBack().build();
        raft[2][4] = new Rower.RowerBuilder("R2").setLeftFront().build();
        raft[3][4] = new Rower.RowerBuilder("R3").setLeftFront().build();
        raft[4][4] = new Rower.RowerBuilder("R4").setLeftFront().build();

        // just to be safe null the seats
        for (int r = 1; r<=4; r++)
            for (int c=1; c<=3; c++)
                raft[r][c]=null;
    }
    private static void loadRowers(ArrayList<Rower> rowers) {
        rowers.clear();
        rowers.add(new Rower.RowerBuilder("A").setLeftBack().setBack().setRightFront().build());
        rowers.add(new Rower.RowerBuilder("B").setLeftFront().setBack().setRightBack().build());

        rowers.add(new Rower.RowerBuilder("D").setLeftBack().setRightFront().build());
        rowers.add(new Rower.RowerBuilder("E").setFront().setLeftBack().setBack().setRightFront().build());
        rowers.add(new Rower.RowerBuilder("F").setLeftBack().setRightBack().build());
        rowers.add(new Rower.RowerBuilder("G").setFront().setLeftBack().setRightFront().build());
        rowers.add(new Rower.RowerBuilder("H").setLeftBack().setBack().setRightBack().build());
        rowers.add(new Rower.RowerBuilder("I").setFront().setLeftFront().setBack().setRightFront().build());
        rowers.add(new Rower.RowerBuilder("J").setFront().setLeftBack().setBack().setRightBack().build());
        rowers.add(new Rower.RowerBuilder("K").setFront().setLeftFront().setRightFront().build());
        rowers.add(new Rower.RowerBuilder("L").setLeftFront().setRightFront().build());

        rowers.add(new Rower.RowerBuilder("C").setFront().setLeftFront().setRightBack().build());
    }

    private static void drawRaft(Rower[][] raft) {
        for (int r=1; r<=4; r++) {
            for (int c = 1; c <= 3; c++) {
                if (raft[r][c] !=null)
                    System.out.print(raft[r][c].getID());
                else
                    System.out.print('*');
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean seatNext(ArrayList<Rower> rowers, Rower[][] raft, int row, int col) {
        return seatNext  (rowers,raft,row,col,false);
    }

    private static boolean seatNext(ArrayList<Rower> rowers, Rower[][] raft, int row, int col,boolean showWork) {
        //if we are at the last seat
        if ((rowers.size() == 1) && (row == 4) && (col == 3)) {
            if (canSeat(rowers.get(0), raft, row, col)) {
                raft[row][col] = rowers.get(0);
                rowers.clear();
                return true;
            } else
                return false;
        } else {
            //try all the rowers that fit on the current seat
            for (int i = 0; i < rowers.size(); i++) {
                if (canSeat(rowers.get(i), raft, row, col)) {
                    //Get the rower out of the collections.
                    Rower candidate = rowers.remove(i);

                    //Seat this rower
                    raft[row][col] = candidate;

                    //Print the raft so far
                    if (showWork)
                    drawRaft(raft);

                    //Figure out what the next seat would be

                    int nextCol, nextRow;
                    if (col < 3) {
                        nextCol = col + 1;
                        nextRow = row;
                    } else {
                        nextCol = 1;
                        nextRow = row + 1;
                    }

                    //See if we can fit the next rowers
                    if (seatNext(rowers, raft, nextRow, nextCol,showWork)) {
                        return true;
                    } else {
                        //Get that Rower back in the list
                        rowers.add(i, candidate);

                        //Get him off the raft
                        raft[row][col] = null;
                    }

                }
            }
            //if we went through all the rowers and couldn't seat one
            return false;
        }

    }

    private static boolean canSeat(Rower testRower, Rower[][] raft, int row, int col) {
        //Function to check if a rower can take a seat
        //Check if the rower is "compatible" with the rowers on all sides.

        //Check rower in front.
        if (raft[row - 1][col] != null)
            if (!(raft[row - 1][col].isBack() ^ testRower.isFront()))
                return false;

        //Check rower in back.
        if (raft[row + 1][col] != null)
            if (!(raft[row + 1][col].isFront() ^ testRower.isBack()))
                return false;

        //Check rower to the left
        if (raft[row][col - 1] != null) {
            if (!(raft[row][col - 1].isRightFront() ^ testRower.isLeftFront()))
                return false;

            if (!(raft[row][col - 1].isRightBack() ^ testRower.isLeftBack()))
                return false;
        }

        //Check rower to the right
        if (raft[row][col + 1] != null) {
            if (!(raft[row][col + 1].isLeftFront()) ^ testRower.isRightFront())
                return false;

            if (!(raft[row][col + 1].isLeftBack() ^ testRower.isRightBack()))
                return false;
        }

        //If all matches!
        return true;
    }


}