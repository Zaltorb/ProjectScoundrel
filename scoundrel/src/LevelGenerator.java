public class LevelGenerator
{
	public static int[][] rngRooms = new int[3][3];
	public static String[][] map = new String[29][29];
	public static boolean[][] mobPlotROOM = new boolean[9][9];
	public static boolean[][] mobPlotMAP = new boolean[29][29];
	
	public static String[][] roomGenerate = new String[9][9];
	
//|LEVEL GENERATOR|// methods used for generating the level
	/**
	 * generates the entire level
	 */
	public static void generateLvl()
	{
		for(int row = 0; row < map.length; row++)//makes all the strings inside map into a space instead of null
		{
			for(int column = 0; column < map.length; column++)
			{
				map[row][column] = " ";
			}//for
		}//for
		for(int row = 0; row < rngRooms.length; row++)//makes all the strings inside map into a space instead of null
		{
			for(int column = 0; column < rngRooms.length; column++)
			{
				rngRooms[row][column] = 0;
			}//for
		}//for
		
		roomPlan();
		RNGconnectorPlan();
		printMap();
	}//generateLvl
	
	/**
	 * Tells where rooms go in the lvl.
	 */
	public static void roomPlan()
	{
		int spawn = 30;
		int spawnROW = (int) (Math.random() * 3);
		int spawnCOLUMN = (int) (Math.random() * 3);
		rngRooms[spawnROW][spawnCOLUMN] = spawn;
		
		int exit = 40;
		boolean loop = true;
		while(loop)
		{
			int exitROW = (int) (Math.random() * 3);
			int exitCOLUMN = (int) (Math.random() * 3);
			if(rngRooms[exitROW][exitCOLUMN] != spawn)
			{
				rngRooms[exitROW][exitCOLUMN] = exit;
				loop = false;
			}
		}//while
		
		int[] compareArr = new int[8];//array is used to make unique rooms in lvl
		for(int row = 0; row < rngRooms.length; row++)
		{
			for(int column = 0; column < rngRooms.length; column++)
			{
				if(rngRooms[row][column] != spawn && rngRooms[row][column] != exit)
				{
					int room = 0;
					boolean good = false;
					while(!good)//true when unique number is made
					{
						boolean dupe = false;
						int rngRoom = (int) (Math.random() * roomLibrary + 1);//random room number
						
						for (int i = 0; i < compareArr.length; i++)//checks if the number is a dupe in the compareArr
						{
							if(compareArr[i] == rngRoom)//dupe becomes true if element equals rngRoom
							{
								dupe = true;
							}
						}//for
						
						if(!dupe)//goes here if it's not a dupe, stops while-loop and adds rngRoom into compareArr
						{
							good = true;
							for (int i = 0; i < compareArr.length; i++)//adds rngRoom into compareArr
							{
								if(compareArr[i] == 0)
								{
									compareArr[i] = rngRoom;
									i =+ 8;//to get i < compareArr.length to stop for-loop
								}
							}//for
							room = rngRoom;
						}//if
					}//while
					rngRooms[row][column] = room;
				}//else
			}//for
		}//for
		
		int[] rowCoord = {0,10,20};
		int[] columnCoord = {0,10,20};
		
		for(int row = 0; row < rngRooms.length; row++)
		{
			for(int column = 0; column < rngRooms.length; column++)
			{
				pickRoom(rngRooms[row][column], rowCoord[row], columnCoord[column]);
			}//for
		}//for
	}//roomPlan
	
	public static String[][] roomCreator;//turns into the room that is needed to be printed into map
	/**
	 * Picks rooms to create depending on room number.
	 * @param room - The room number
	 * @param row - row to start the print
	 * @param column - column to start the print
	 */
	public static void pickRoom(int room, int row, int column)
	{
		if(room == 30) {
			roomCreator = roomSpawn;
		}else if(room == 40) {
			roomCreator = roomExit;
		}else if(room == 1) {
			roomCreator = roomA1;
		}else if(room == 2) {
			roomCreator = roomA2;
		}else if(room == 3) {
			roomCreator = roomA3;
		}else if(room == 4) {
			roomCreator = roomA4;
		}else if(room == 5) {
			roomCreator = roomA5;
		}else if(room == 6) {
			roomCreator = roomA6;
		}else if(room == 7) {
			roomCreator = roomA7;
		}else if(room == 8) {
			roomCreator = roomA8;
		}else if(room == 9) {
			roomCreator = roomA9;
		}//else if
		setMobs();
		generateRoom(row, column);
	}//pickRoom
	
	public static void setMobs()
	{
		for(int row = 0; row < mobPlotROOM.length; row++)
		{
			for(int column = 0; column < mobPlotROOM.length; column++)
			{
				mobPlotROOM[row][column] = false;
			}//for
		}//for
		
		int maxInRoom = 3;
		boolean loop = true;
		while(loop)
		{
			int spawnROW = (int) ((Math.random() * 7) + 1);
			int spawnCOLUMN = (int) ((Math.random() * 7) + 1);
			
			if(roomCreator[spawnROW][spawnCOLUMN] == "." && roomCreator[spawnROW][spawnCOLUMN] != "@" && roomCreator[spawnROW][spawnCOLUMN] != "H" && mobPlotROOM[spawnROW][spawnCOLUMN] == false)
			{
				//roomCreator[spawnROW][spawnCOLUMN] = "E";
				mobPlotROOM[spawnROW][spawnCOLUMN] = true;
				maxInRoom -= 1;
			}
			if(maxInRoom == 0)
			{
				loop = false;
			}
		}
	}//setMobs
	/**
	 * Places the rooms into the map.
	 * @param rowOrigin - row to start the print
	 * @param columnOrigin - column to start the print
	 */
	public static void generateRoom(int rowOrigin, int columnOrigin)
	{
		for(int row = rowOrigin; row < roomGenerate.length+rowOrigin; row++)
		{
			for(int column = columnOrigin; column < roomGenerate.length+columnOrigin; column++)
			{
				map[row][column] = roomCreator[row-rowOrigin][column-columnOrigin];
				mobPlotMAP[row][column] = mobPlotROOM[row-rowOrigin][column-columnOrigin];
			}//for
		}//for
	}//generateRoom
	
	//|NORMAL PATHS|//
	public static String[][] connectorCreator;//turns into paths that are needed to be printed into map
	/**
	 * Places the paths into the level.
	 */
	public static void RNGconnectorPlan()
	{
		int rng = (int) (Math.random() * connectorLibrary + 1);
		if(rng == 1) {
			connectorPlans = plan1;
		}else if(rng == 2) {
			connectorPlans = plan2;
		}else if(rng == 3) {
			connectorPlans = plan3;
		}else if(rng == 4) {
			connectorPlans = plan4;
		}else if(rng == 5) {
			connectorPlans = plan5;
		}
		
		int[][] rowCoords =
			{
					{00,3,00,3,00},
					{8,00,8,00,8},
					{00,13,00,13,00},
					{18,00,18,00,18},
					{00,23,00,23,00},
			};
		int[][] columnCoords =
			{
					{00,8,00,18,00},
					{3,00,13,00,23},
					{00,8,00,18,00},
					{3,00,13,00,23},
					{00,8,00,18,00},
			};
		for(int row = 0; row < connectorPlans.length; row++)
		{
			for(int column = 0; column < connectorPlans.length; column++)
			{
				if(connectorPlans[row][column] == 1)
				{
					if(row == 1 || row == 3)
					{
						connectorCreator = connectorV;
					}
					else
					{
						connectorCreator = connectorH;
					}
					generateConnector(rowCoords[row][column],columnCoords[row][column]);
				}//if
			}//for
		}//for
	}//RNGconnectorPlan
	
	/**
	 * Randomly places paths that connect rooms in the map.
	 * @param rowOrigin - row to start the print
	 * @param columnOrigin - column to start the print
	 */
	public static void generateConnector(int rowOrigin, int columnOrigin)
	{
		int pathLW = 3;
		for(int row = rowOrigin; row < pathLW+rowOrigin; row++)
		{
			for(int column = columnOrigin; column < pathLW+columnOrigin; column++)
			{
				if(connectorCreator[row-rowOrigin][column-columnOrigin] != " ")
				{
					map[row][column] = connectorCreator[row-rowOrigin][column-columnOrigin];
				}
			}//for
		}//for
	}//generateConnector
	
	public static void cls() {
		try {	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		} catch(Exception E) {
				System.out.println(E);
		}
	}
	
	/**
	 * Prints out the Map
	 */
	public static void printMap()
	{
		cls();
		
		for(int row = 0; row < map.length; row++)
		{
			for(int column = 0; column < map.length; column++)
			{
				System.out.print(map[row][column] + " ");
			}//for
			System.out.println();
		}//for
		System.out.println();
	}//printMap
	
	public static void printMobMap()
	{
		for(int row = 0; row < map.length; row++)
		{
			for(int column = 0; column < map.length; column++)
			{
				if(mobPlotMAP[row][column] == true)
				{
					System.out.print("E ");
				}
				else
				{
					System.out.print(". ");
				}
			}//for
			System.out.println();
		}//for
	}//printMap
	
//|ROOMS|// room arrays, for room making uwu
/**
 * BUILDING BLOCKS
 * _ AND | = walls
 * @return 
 * @ = player
 * . = walkable surface (floor)
 * think of other ones for me thanks :^)
 * 
 *  when designing, keep in mind that there could be entrances in all four walls.
 */
	
	/**
	 * BE SURE TO CHANGE roomLibrary WHEN MAKNIG NEW ROOMS
	 */
	public static int roomLibrary = 9;//number of rooms we have to pick from
	
	/*
	 * copy roomTemplate to make other rooms Rea :)
	 */
	public static String[][] roomTemplate =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|","_","_","_","_","_","_","_","|"},
		};
	public static String[][] roomSpawn =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".","[","@","]",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|","_","_","_","_","_","_","_","|"},
		};
	public static String[][] roomExit =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|"," ",".",".",".",".","."," ","|"},
				{"|",".","."," ","."," ",".",".","|"},
				{"|","."," "," ","."," "," ",".","|"},
				{"|","."," ","[","X","]"," ",".","|"},
				{"|","."," "," ","."," "," ",".","|"},
				{"|",".","."," ","."," ",".",".","|"},
				{"|"," ",".",".",".",".","."," ","|"},
				{"|","_","_","_","_","_","_","_","|"},
		};
	public static String[][] roomA1 =
		{
				{" "," "," "," ","_"," "," "," "," "},
				{" "," "," ","|",".","|"," "," "," "},
				{" "," "," ","|",".","|"," "," "," "},
				{"_","_","_","|",".","|","_","_","_"},
				{"|",".",".",".","H",".",".",".","|"},
				{"|","_","_"," ","."," ","_","_","|"},
				{" "," "," ","|",".","|"," "," "," "},
				{" "," "," ","|",".","|"," "," "," "},
				{" "," "," ","|","_","|"," "," "," "},
		};
	public static String[][] roomA2 =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".","_","_","_","_","_",".","|"},
				{"|",".","|"," "," "," ","|",".","|"},
				{"|",".","|"," "," "," ","|",".","|"},
				{"|",".","|"," "," "," ","|",".","|"},
				{"|",".","|","_","_","_","|",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|","_","_","_","_","_","_","_","|"},
		};
	public static String[][] roomA3 =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|","H",".","|",".",".",".",".","|"},
				{"|",".",".","|"," ",".",".",".","|"},
				{"|","_",".","|"," ",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".","."," ","_",".","_","|"},
				{"|",".",".","."," ","|",".",".","|"},
				{"|",".",".",".",".","|",".","H","|"},
				{"|","_","_","_","_","|","_","_","|"},
		};
	public static String[][] roomA4 =
		{
				{" "," "," ","_","_","_","_","_"," "},
				{" "," "," ","|",".",".",".",".","|"},
				{" "," ","_","|",".","_","_",".","|"},
				{"_","_","|",".",".",".","|",".","|"},
				{"|",".","|",".","H",".","|",".","|"},
				{"|",".","|",".",".",".","|","_","|"},
				{"|",".","|","_",".","_","|"," "," "},
				{"|",".",".",".",".","|"," "," "," "},
				{"|","_","_","_","_","|"," "," "," "},
		};
	public static String[][] roomA5 =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".",".",".","|",".",".",".","|"},
				{"|",".",".","H","|",".",".",".","|"},
				{"|",".","_","_","|","_","_",".","|"},
				{"|",".",".",".","|","H",".",".","|"},
				{"|",".",".",".","|",".",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|","_","_","_","_","_","_","_","|"},
		};
	public static String[][] roomA6 =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".","."," "," "," ",".",".","|"},
				{"|","."," "," "," "," "," ",".","|"},
				{"|","."," "," "," "," "," ",".","|"},
				{"|","."," "," "," "," "," ",".","|"},
				{"|",".","."," "," "," ",".",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|","_","_","_","_","_","_","_","|"},
		};
	public static String[][] roomA7 =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|","H","|"," ",".",".",".",".","|"},
				{"|",".","|"," ","."," ","|",".","|"},
				{"|",".","|"," ","."," ","|",".","|"},
				{"|",".","|"," ","."," ","|",".","|"},
				{"|",".","|"," ","."," ","|",".","|"},
				{"|",".","|"," ","."," ","|",".","|"},
				{"|",".",".",".","."," ","|","H","|"},
				{"|","_","_","_","_","_","|","_","|"},
		};
	public static String[][] roomA8 =
		{
				{" ","_","_","_","_","_","_","_"," "},
				{"|",".",".",".",".",".",".",".","|"},
				{"|",".","_","_","_","_","_","_","|"},
				{"|",".","|",".",".",".",".",".","|"},
				{"|",".","|","_",".","_","_",".","|"},
				{"|",".",".",".",".",".","|",".","|"},
				{"|","_","_","_","_","_","|",".","|"},
				{"|",".",".",".",".",".",".",".","|"},
				{"|","_","_","_","_","_","_","_","|"},
		};
	public static String[][] roomA9 =
		{
				{" "," "," ","_","_","_"," "," "," "},
				{" "," "," "," ","."," "," "," "," "},
				{" "," "," "," ",".",".",".","."," "},
				{"|",".",".","."," ","."," ",".","|"},
				{"|","."," ",".",".","."," ",".","|"},
				{"|",".","."," "," ",".","."," ","|"},
				{" "," ",".","."," "," ","."," "," "},
				{" ",".",".",".",".",".","."," "," "},
				{" "," "," ","_","_","_"," "," "," "},
		};
	
//|CONNECTOR PLANS|//
	public static String[][] connectorH =
		{
				{" ","_"," "},
				{".",".","."},
				{"_","_","_"},
		};
	
	public static String[][] connectorV =
		{
				{" ","."," "},
				{"|",".","|"},
				{"|",".","|"},
		};
	
	public static int[][] connectorPlans = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,1,2},
				{1,3,1,3,1},
				{2,1,2,1,2},
				{1,3,1,3,1},
				{2,1,2,1,2},
		};
	/**
	 * BE SURE TO CHANGE connectorLibrary WHEN MAKNIG NEW PATHS
	 */
	public static int connectorLibrary = 5;//number of pathway plans we have to pick from
	
	public static int[][] plan1 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,1,2},
				{0,3,1,3,0},
				{2,1,2,1,2},
				{1,3,1,3,1},
				{2,1,2,0,2},
		};
	public static int[][] plan2 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,1,2},
				{1,3,0,3,1},
				{2,1,2,0,2},
				{1,3,1,3,1},
				{2,0,2,0,2},
		};
	public static int[][] plan3 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,1,2},
				{1,3,0,3,1},
				{2,0,2,0,2},
				{0,3,1,3,1},
				{2,1,2,1,2},
		};
	public static int[][] plan4 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,1,2},
				{0,3,1,3,1},
				{2,1,2,1,2},
				{1,3,0,3,1},
				{2,1,2,0,2},
		};
	public static int[][] plan5 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,0,2,1,2},
				{1,3,1,3,1},
				{2,0,2,1,2},
				{1,3,1,3,0},
				{2,1,2,1,2},
		};
	public static int[][] plan6 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,0,2,1,2},
				{1,3,1,3,0},
				{2,1,2,0,2},
				{1,3,1,3,1},
				{2,0,2,1,2},
		};
	public static int[][] plan7 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,1,2},
				{0,3,1,3,1},
				{2,1,2,0,2},
				{1,3,0,3,1},
				{2,1,2,0,2},
		};
	public static int[][] plan8 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,1,2},
				{1,3,0,3,1},
				{2,1,2,1,2},
				{1,3,0,3,0},
				{2,1,2,1,2},
		};
	public static int[][] plan9 = //2 = rooms | 1 = paths | 3 = spaces
		{
				{2,1,2,0,2},
				{0,3,1,3,1},
				{2,1,2,1,2},
				{0,3,0,3,1},
				{2,1,2,1,2},
		};
}//levelGenerator
