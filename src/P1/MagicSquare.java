package P1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MagicSquare {

	public static void main(String[] args) throws IOException {
		MagicSquare mg = new MagicSquare();
		boolean test = mg.isLegalMagicSquare("D:\\SoftwareConstruction\\coderepo\\src\\P1\\txt\\1.txt");
		boolean test1 = mg.isLegalMagicSquare("D:\\SoftwareConstruction\\coderepo\\src\\P1\\txt\\2.txt");
		boolean test2 = mg.isLegalMagicSquare("D:\\SoftwareConstruction\\coderepo\\src\\P1\\txt\\3.txt");
		boolean test3 = mg.isLegalMagicSquare("D:\\SoftwareConstruction\\coderepo\\src\\P1\\txt\\4.txt");
		boolean test4 = mg.isLegalMagicSquare("D:\\SoftwareConstruction\\coderepo\\src\\P1\\txt\\5.txt");
		System.out.println(test);
		System.out.println(test1);
		System.out.println(test2);
		System.out.println(test3);
		System.out.println(test4);
		if( MagicSquare.generateMagicSquare(-1)){
			boolean test5 = mg.isLegalMagicSquare("D:\\SoftwareConstruction\\coderepo\\src\\P1\\txt\\6.txt");
			System.out.println(test5);
		}
	}
	@SuppressWarnings("resource")
	
	boolean isLegalMagicSquare(String fileName) throws IOException
	{
		//数值读取以及准备工作
		File file = new File(fileName);
		BufferedReader bfr;
		String read = new String();
		List<Integer> valuebox= new ArrayList<>();
		int colony=0;
		int row =1;
		bfr = new BufferedReader(new FileReader(file));
		if((read = bfr.readLine()) != null)
		{
			String[] num = read.split("\t");
			for(String tep :num){
				colony++;
				try{
					valuebox.add(Integer.valueOf(tep));
				}
				catch(Exception e)
				{
					System.out.println("Not a number!");
					return false;
				}
			}
		}
		
		while((read = bfr.readLine()) != null) //同理如上
		{
			String[] num = read.split("\t");
			int tag=0;
			for(String tep :num){
				try{
					valuebox.add(Integer.valueOf(tep));
				}
				catch(Exception e)
				{
					System.out.println("Not a number!");
					return false;
				}
				tag++;
			}
			if(tag !=colony){//tag为统计每一行的元素个数的变量
				bfr.close();
				System.out.println("It is not a  matrix");
				return false;
			}
			row++;
		}
		
		if(row!=colony)//row为行数，colony为列数
		{
			bfr.close();
			System.out.println("The row and the colony aren't equal");
			return false;
		}
		
		//进行数值的检验
		int [][] valuebox2 = new int[row][colony];
		for(int i =0;i<row;i++)
		{
			for(int j=0;j<colony;j++)
			{
				valuebox2[i][j] = valuebox.get(i*colony+j);
			}
		}
		
		int standard= 0,diagonal1=0,diagonal2=0;
		for(int j=0;j<colony;j++)
		{
			standard+=valuebox2[0][j];
		}
		
		for(int i=0;i<row;i++)  //test the row 
		{
			int rowtest=0;
			for(int j=0;j<colony;j++){
				rowtest+=valuebox2[i][j];
			}
			if(rowtest!=standard){
				bfr.close();
				System.out.println("The sum of one row is not equal as the standard");
				return false;
			}
		}
		
		for(int j=0;j<colony;j++) //test the colony
		{
			int colonytest=0;
			for(int i=0;i<row;i++){
				colonytest+=valuebox2[i][j];
			}
			if(colonytest!=standard){
				bfr.close();
				System.out.println("The sum of one colony does not equal to the standard ");
				return false;
			}
		}
		
		for(int i=0;i<row;i++) //test the diagonal
		{
			for(int j=0;j<colony;j++)
			{
				if(i==j)
					diagonal1+=valuebox2[i][j];
				if(i==row-j-1)
					diagonal2+=valuebox2[i][j];
			}
		}
		if(diagonal1!=standard||diagonal2!=standard)
		{
			bfr.close();
			System.out.println("The sum of one diagonal is not equal as the standard ");
			return false;	
		}	
		return true;
	}

public static boolean generateMagicSquare(int n) throws IOException {
	if(n<0)
	{
		System.out.println("Please input a positive number");
		return false;
	}
	 int magic[][] = new int[n][n];
	 int row = 0, col = n / 2, i, j, square = n * n;
	 try{
		 for (i = 1; i <= square; i++) {
			 magic[row][col] = i;
			 if (i % n == 0)
				 row++;
			 else {
				 if (row == 0)
					 row =n-1;
				 else
					 row--;
				 if (col == (n-1))
					 col = 0;
				 else
					 col++;
			 }
		 } 
	 }
	 catch (Exception e) {
		 System.out.println("Please input an odd");
		 return false;
	}
	 
	 FileWriter fw =new FileWriter("D:\\MagicSquare\\src\\P1\\6.txt");
	 for (i = 0; i < n; i++) {
		 for (j = 0; j < n; j++){
			 fw.write(magic[i][j]+"\t");
		 }
		 fw.write("\n");
	 }
	 fw.close();
	 return true;
	}
}