import com.sun.jdi.IntegerType;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int[] rowCol = Arrays.stream(br.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
       int row=rowCol[0];
       int col = rowCol[1];
        char[][] matrix = new char[row][col];
       for(int i =0; i < row;i++){
           matrix[i] =br.readLine().toCharArray();
       }
        char[][] matr =new char[matrix.length][matrix[0].length];
       int[] elemGenerations = Arrays.stream(br.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        NextGeneration nextGeneration = new  NextGeneration(matrix,elemGenerations[2],elemGenerations[0],elemGenerations[1],matr);
       int count = nextGeneration.generations();
       System.out.println(count);
    }

}
