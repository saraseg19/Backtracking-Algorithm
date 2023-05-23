import java.util.*;


public class SeguraBacktrackAlgorithm {

	public static int maxProfit = 0;
	public static int WLimit;
	public static int numBest;
	public static boolean [] bestSet;
	public static int [] p;
	public static int [] w;
	public static int n;
	public static boolean [] include;

	public static void main(String[] args)
	{
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter a number to generate the arrays: ");
			n = scan.nextInt();

			p = new int[n+1];
			w = new int[n+1];
			include = new boolean[n+1];
			bestSet = new boolean[n+1];

			//for(int i = 0; i < 11; i++)
			//{
			generateArray(p,w);	

			System.out.println(); System.out.println("Your arrays:");
			System.out.print("Profits: "); printIntArray(p); System.out.println();
			System.out.print("Weights: "); printIntArray(w); System.out.println();

			System.out.println();

			System.out.println("Sorting...");
			sortArrays();
			System.out.print("Profits: "); printIntArray(p); System.out.println();
			System.out.print("Weights: "); printIntArray(w); System.out.println();

			System.out.println();

			System.out.println("Enter the knapsack weight limit:");
			WLimit = scan.nextInt();

			knapsack(0, p[0], w[0]);

			System.out.println("---------------------------------------------------------------------");
			System.out.println();

			System.out.print("BestSet: "); printArrayList(bestList(bestSet)); System.out.println();
			System.out.print("MaxProfit: $" + maxProfit); System.out.println();
			scan.close();
		}
		catch(Exception e)
		{
			System.out.println("An error has occurred, please run program again.");
		}
	}


	public static void knapsack(int i, int profit, int weight)
	{

		if(weight <= WLimit && profit > maxProfit)
		{
			maxProfit = profit;
			numBest = i;
			bestSet = Arrays.copyOf(include, bestSet.length);
		}

		if(promising(i) && i < n)
		{
			include[i+1] = true;
			System.out.println("Included node");
			System.out.println("Number of items included: " + numBest);
			System.out.println();

			System.out.print("BestSet: "); printArray(bestSet); System.out.println();
			System.out.print("Included: "); printArray(include); System.out.println();
			System.out.print("MaxProfit: " + maxProfit); System.out.println(); System.out.println();
			System.out.println("---------------------------------------------------------------------");

			knapsack(i+1, profit + p[i+1], weight + w[i+1]); 

			include[i+1] = false;

			System.out.println("---------------------------------------------------------------------");
			System.out.println("Number of items included: " + numBest);
			System.out.println();

			System.out.print("BestSet: "); printArray(bestSet); System.out.println();
			System.out.print("Included: "); printArray(include); System.out.println();
			System.out.print("MaxProfit: " + maxProfit); System.out.println(); System.out.println();

			knapsack(i+1, profit, weight);
		}

	}

	public static boolean promising(int i)
	{
		int j; int k;
		int totWeight = 0;
		float bound = 0;
		float profit;
		float node;

		if(w[i] >= WLimit)
			return false;
		else
		{
			j = i + 1;

			bound = p[i];											//setting bound at profit at index i
			totWeight = w[i];										//setting total weight at weight at index i

			for(int wp = 0; wp < include.length; wp++)				//Checking to see if other elements are included in 
			{														//bound and totWeight
				if(wp == i)											//If loop gets to the index, break it, so no repeated values	
					break;
				if(include[wp] == true)								//If other elements are included, then add them to total weight and bound
				{
					totWeight = totWeight + w[wp];
					bound = bound + p[wp];
				}
			}


			if(include[i] == true)									//Node location
				node = (float)(i + 0.1);
			else
			{
				bound = bound - p[i];
				totWeight = totWeight - w[i];
				node = (float)(i + 0.2);
				if(i == 0)
					node = 0;
			}

			profit = bound;		

			System.out.println(); System.out.println("Profit at " + node + " : " + profit); //Checking total profits so far
			System.out.println("Total Weight: " + totWeight); System.out.println(); 		//Checking total weight so far

			if(totWeight >= WLimit)
			{
				System.out.println("Node not included"); System.out.println();
				return false;
			}

			while(j <= n && (totWeight + w[j]) <= WLimit)			//Calculating future values						
			{
				totWeight = totWeight + w[j];
				bound = bound + p[j];
				j++;
			}

			k=j;

			if(k<=n)
			{
				System.out.println("Bound at " + node + " = " + bound + " + " + "((" + WLimit + "-" + totWeight + ")*(" + p[k] + "/" + w[k] + "))"); 			
				System.out.println("             = " + bound + " + " + "((" + (WLimit - totWeight) + ")*(" + (p[k]/w[k]) + "))");
				System.out.println("             = " + bound + " + " + "(" + (WLimit - totWeight) * (p[k]/w[k]) + ")");
				bound = bound + ((WLimit-totWeight)*(p[k]/w[k]));

				System.out.println("             = " + bound); System.out.println();
			}        

			if((bound > maxProfit) == false)
				System.out.println("Node not included"); System.out.println();

				return bound > maxProfit;
		}
	}

	public static void sortArrays()
	{
		HashMap<Integer, Integer> pw = new HashMap<Integer, Integer>();
		int[] order = new int[n];
		int level;
		int value;

		for(int i = 1; i <= n; i++)
		{
			level = i;
			value = p[i]/w[i];

			pw.put(level, value);
		}


		System.out.println();
		System.out.print("Index: "); System.out.println(pw.keySet());
		System.out.print("p/w: "); System.out.println(pw.values());
		System.out.println();


		level = 0;

		for(int i = 0; i < n; i++)
		{
			level = hashMax(pw);
			order[i] = level;
			pw.remove(level);
		}

		int [] p2 = new int[n+1];
		int [] w2 = new int[n+1];

		for(int i = 0; i < n; i++)
		{
			int o = order[i];
			//System.out.print(o);
			p2[i+1] = p[o];
			w2[i+1] = w[o];
		}

		/*
			System.out.println();

			System.out.print("p2: ");
			printArray(p2);
			System.out.println();
			System.out.print("w2: ");
			printArray(w2);
			System.out.println();
		 */		

		for(int i = 1; i <= n; i++)
		{
			p[i] = p2[i];
			w[i] = w2[i];
		}

	}

	public static int hashMax(HashMap<Integer, Integer> h)
	{
		int m = -1;
		int k = -1;

		for(Map.Entry<Integer, Integer> mapElement : h.entrySet())
		{
			if(m < mapElement.getValue())
			{
				m = mapElement.getValue();
				k = mapElement.getKey();
			}
		}

		//System.out.println(k);
		return k;
	}

	public static void generateArray(int[] arr, int[] a)
	{
		int values = 0;

		for(int i = 1; i <= n; i++)
		{
			values = (int)(Math.random()*100);
			arr[i] = values;
			values = (int)(Math.random()*15);
			a[i] = values;
			if(a[i] == 0)
			{
				a[i] = 1;
			}
		}	
	}

	public static ArrayList<Integer> bestList(boolean[] b)
	{
		ArrayList<Integer> best = new ArrayList<Integer>();
		best.add(0);

		for(int i = 1; i < b.length; i++)
		{
			if(b[i] == true)
				best.add(i);
		}

		return best;
	}

	public static void printIntArray(int[] arr)
	{
		System.out.print("[");

		for(int i = 0; i <= n-1; i++)
		{
			System.out.print(arr[i] + ", ");
		}

		System.out.print(arr[n] + "]");
	}

	public static void printArray(boolean[] arr)
	{
		System.out.print("[");

		for(int i = 0; i <= n-1; i++)
		{
			System.out.print(arr[i] + ", ");
		}

		System.out.print(arr[n] + "]");
	}

	public static void printArrayList(ArrayList<Integer> arr)
	{
		System.out.print("[");

		for(int i = 0; i < arr.size()-1; i++)
		{
			System.out.print(arr.get(i) + ", ");
		}

		System.out.print(arr.get(arr.size()-1) + "]");
	}
}