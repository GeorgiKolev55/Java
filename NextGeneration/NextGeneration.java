public class NextGeneration {
    char[][] matrix;
    int row;
    int col;
    int gen;
    char[][] nextMatrix;
    public NextGeneration(char[][] matrix,int gen,int row,int col,char[][] nextMatrix) {
        this.matrix=matrix;
        this.row=row;
        this.col=col;
        this.gen=gen;
        this.nextMatrix=nextMatrix;
    }

    public  int generations(){
        int times =0;
        for (int t=0;t<this.gen;t++){
            //if (matrix[row][col]=='1'&&t==0){times++;}
            for (int i =0;i<this.matrix.length;i++){
                for (int k =0;k<matrix[i].length;k++){
                    char color= greenOrRed(matrix,i,k);
                    nextMatrix[i][k]=color;
                }
            }
            matrix=new char[nextMatrix.length][nextMatrix[0].length];
            fillNextGeneration(matrix,nextMatrix);

            if (matrix[row][col]=='1'){times++;}

        }
        return times;
    }
    private  void fillNextGeneration(char[][] matrix, char[][] nextMatrix) {
        for (int i =0;i<matrix.length;i++){
            for (int d =0;d<matrix[i].length;d++){
                matrix[i][d]=nextMatrix[i][d];
            }
        }
    }

    private  char greenOrRed(char[][] matrix,int r,int c) {
        int greenCount=0;
        if (isInBounds(matrix,r+1,c)){
            if(matrix[r+1][c]=='1'){greenCount++;}

        }
        if (isInBounds(matrix,r+1,c-1)){
            if(matrix[r+1][c-1]=='1'){greenCount++;}

        }
        if (isInBounds(matrix,r+1,c+1)){
            if(matrix[r+1][c+1]=='1'){greenCount++;}

        }
        if (isInBounds(matrix,r-1,c-1)){
            if(matrix[r-1][c-1]=='1'){greenCount++;}

        }
        if (isInBounds(matrix,r,c-1)){
            if(matrix[r][c-1]=='1'){greenCount++;}

        }
        if (isInBounds(matrix,r,c+1)){
            if(matrix[r][c+1]=='1'){greenCount++;}

        }
        if (isInBounds(matrix,r-1,c)){
            if(matrix[r-1][c]=='1'){greenCount++;}

        }
        if (isInBounds(matrix,r-1,c+1)){
            if(matrix[r-1][c+1]=='1'){greenCount++;}

        }
        char redGreen = findColor(matrix[r][c],greenCount);
        return redGreen;
    }

    private static char findColor(char matrix,int greenCount) {
        char toReturn = ' ';
        if (matrix=='1'){
            if (greenCount==0||greenCount==1||greenCount==4||greenCount==5||greenCount==7||greenCount==8){
                toReturn='0';
            }
            else if (greenCount==2||greenCount==3||greenCount==6){
                toReturn='1';}
        }
        else if(matrix=='0'){
            if (greenCount==3||greenCount==6){
                toReturn='1';
            }
            else if(greenCount==0||greenCount== 1||greenCount== 2||greenCount== 4||greenCount== 5||greenCount== 7||greenCount== 8){
                toReturn='0';
            }
        }
        return toReturn;
    }


    private static boolean isInBounds(char[][] matrix, int r, int c) {
        return matrix.length>r &&r>=0&&c>=0&&c<matrix[r].length;
    }
}
