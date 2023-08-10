 package cricbuzz;
public class Extra {
	public static void main(String[] args) {
		String extrasString = " (b 0, lb 10, w 5, nb 0, p 0)";
		String[] cutString1 = extrasString.split(" ");
		int size = cutString1.length;
		int sum = 0;
		for (int i = 2; i < size; i += 2) {
			String eachString = cutString1[i].trim();

			String[] cutString2 = eachString.split(",");
			String runs = cutString2[0].replace(")", "");
			int intRun = Integer.parseInt(runs);
			sum = sum + intRun;
		}
		System.out.println("Total of extra runs - "+sum);
	}
}
//extra
//commit
 //r
///other changes
 ///kalyan

//Barote
