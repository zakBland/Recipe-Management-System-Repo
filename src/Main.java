import java.util.Scanner;
import java.nio.*;

public class Main {

	public static void main(String[] args) {
		
		
		//new Display();
		
		//see if numbers up to 2K and bits less than  == 0 after bitwise
		bitwise();
		
	}
	
	public static void bitwise() {
		Scanner scanner = new Scanner(System.in);
		
		int elementsSize = scanner.nextInt();
		int K = scanner.nextInt();
		int num = Integer.parseInt("2" +(K + ""));
		int length = 0;
		int max = 0;
		
		for(int i = 0; i < num; i++) {
			length = calculateMaxNum(i);
			System.out.println("length " + length);
			if(length <= elementsSize) {
				max = i;
			}
		}
		
		System.out.println(max);
	}
	
	public static int calculateMaxNum(int num) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(num).array();
		int temp = 0;
		
		for(int i = 0; i < bytes.length; i++) {
			if(bytes[i] == 1) {
				temp = bytes.length - i - 1;
			}
			
			System.out.println("bytes[" + i +"] is " + bytes[i]);
			
		}
		
		return temp;
	}
	

}
