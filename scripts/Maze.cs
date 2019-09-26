using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
public class Maze : MonoBehaviour
{
    [System.Serializable]
    public class Cell {
        public bool visited;
        public GameObject north; //1
        public GameObject south; //4
        public GameObject east;  //2
        public GameObject west;  //3
    }


    public GameObject wall;
    private float wallLength = 1.0f;
    private int xSize = 30;
    private int ySize = 30;
    private Vector3 initialPos;
    private GameObject wallHolder;
    private Cell[] cells;
    private int currentCell = 0;
    private int totalcells;
    private int cellsVisited = 0;
    private bool startedBuilding = false;
    private int currentNeigh = 0;
    private List<int> finalCell;
    private int backUp = 0;

  //  public GameObject Floor;
    //public Rigidbody player;
  //  private GameObject floorHolder;





    private GameObject[] allWalls;


    public GameObject Cheese;
    private GameObject CheeseHolder;
 
    
    
 
    
    private int wallBreak = 0;
    // Start is called before the first frame update
    void Start()
    {
        CreateWalls();
        

       // initialPos = new Vector3((-xSize/2) + wallLength/2,0f,(0.0f));
       //player = Instantiate(player,initialPos,Quaternion.identity);
       //playerObject = player.AddComponent<Rigidbody>();
       //playerObject.mass = 5;
    }

    void CreateWalls (){
        wallHolder = new GameObject();
        wallHolder.name = "Maze";

        initialPos = new Vector3((-xSize/2) + wallLength/2,0f,(-ySize/2)+ wallLength /2);
        Vector3 myPos = initialPos;
        GameObject tempWall;
        //building walls x-axis
        for(int i = 0; i < ySize; i++){
            for(int j = 0; j <= xSize; j++){
                myPos = new Vector3 (initialPos.x + (j*wallLength) - wallLength/2,0.0f,initialPos.z + (i*wallLength) - wallLength/2);
                tempWall = Instantiate(wall,myPos,Quaternion.identity) as GameObject;
                tempWall.transform.parent = wallHolder.transform;
            }
        }
        //building more walls y-axis
        for(int i = 0; i <=ySize; i++){
            for(int j = 0; j < xSize; j++){
                myPos = new Vector3 (initialPos.x + (j*wallLength),0.0f,initialPos.z + (i*wallLength) - wallLength);
                tempWall = Instantiate(wall,myPos,Quaternion.Euler(0.0f,90,0.0f)) as GameObject;
                tempWall.transform.parent = wallHolder.transform;
            }
        }
        CreateCells();
      //  createFloor();
        SpawnCheese();
    }
    void CreateCells(){
        finalCell = new List<int>();
        finalCell.Clear();
        totalcells = xSize * ySize;
        int children = wallHolder.transform.childCount;
        allWalls = new GameObject[children];
        cells = new Cell[xSize * ySize];
        int eastWestProc = 0;
        int childProcess = 0;
        int countTerm = 0;
        //finds all children
        for(int i = 0; i < children; i++){
            allWalls[i] = wallHolder.transform.GetChild(i).gameObject;
        }
        //walls get cells
        for(int i = 0; i < cells.Length; i++){
            if(countTerm == xSize){
                eastWestProc ++;
                countTerm = 0;
            }
            cells[i] = new Cell ();
            cells[i].east = allWalls[eastWestProc];
            cells[i].south = allWalls[childProcess + (xSize + 1) * ySize];
            eastWestProc ++;
            
            countTerm ++;
            childProcess ++;
            cells[i].west = allWalls[eastWestProc];
            cells[i].north = allWalls[(childProcess + (xSize+1) * ySize)+xSize-1];
        }
        CreateMaze();
    }

    void CreateMaze () {

        while(cellsVisited < totalcells){
            if(startedBuilding){
                findNeighour();
                if (cells[currentNeigh].visited == false && cells[currentCell].visited == true){
                    BreakWall();
                    cells[currentNeigh].visited = true;
                    cellsVisited ++;
                    finalCell.Add(currentCell);
                    currentCell = currentNeigh;
                    if(finalCell.Count > 0){
                        backUp = finalCell.Count - 1;
                    }
                }
            }
            else{
                currentCell = UnityEngine.Random.Range(0 , totalcells);
                cells[currentCell].visited = true;
                cellsVisited ++;
                startedBuilding = true;
            }
            //Invoke("CreateMaze",0.0f);
        }
        //createFloor();
        //createPlayer();
        Debug.Log("finished maze");
        findNeighour();
        
    }
    void BreakWall(){
        switch(wallBreak){
            case 1 : Destroy(cells[currentCell].north); break;
            case 2 : Destroy(cells[currentCell].east); break;
            case 3 : Destroy(cells[currentCell].west); break;
            case 4 : Destroy(cells[currentCell].south); break;
        }
    }

    void findNeighour(){
        
        int foundNeigbour = 0;
        int[] neigbour = new int[4];
        int[] connectingWall = new int[4];
        int check = 0;
        check = (currentCell + 1)/ xSize;
        check -= 1;
        check *= xSize;
        check += xSize;
        //west wall found
        if(currentCell + 1 < totalcells && (currentCell + 1) != check){
           // Debug.Log("first if");
            if(cells[currentCell + 1].visited == false){
                neigbour[foundNeigbour] = currentCell + 1;
                connectingWall[foundNeigbour] = 3;
                foundNeigbour ++;
                //Debug.Log("secound if");
            }
        }
                //east wall found
         if(currentCell - 1 >= 0 && currentCell != check){
            if(cells[currentCell - 1].visited == false){
                neigbour[foundNeigbour] = currentCell - 1;
                connectingWall[foundNeigbour] = 2;
                foundNeigbour ++;
            }
        }
        // north
        if(currentCell +xSize < totalcells){
            if(cells[currentCell + xSize].visited == false){
                neigbour[foundNeigbour] = currentCell + xSize;
                connectingWall[foundNeigbour] = 1;
                foundNeigbour ++;
            }
        }
        //south
        if(currentCell - xSize >= 0){
            if(cells[currentCell - xSize].visited == false){
                neigbour[foundNeigbour] = currentCell - xSize;
                connectingWall[foundNeigbour] = 4;
                foundNeigbour ++;
            }
        } 
        if(foundNeigbour != 0){
            //Random r = new Random();
           // int rint = r.Next(0,Length);
            int chosen = UnityEngine.Random.Range(0,foundNeigbour);
            currentNeigh = neigbour[chosen];
            wallBreak = connectingWall[chosen];
        }
        else{
            if(backUp > 0){
                currentCell = finalCell[backUp];
                backUp --;
            }
        }
       
    }

   /* void createFloor(){
        floorHolder = new GameObject();
        floorHolder.name = "Floor";

        initialPos = new Vector3((-xSize/2) + wallLength/2,0.0f,-7.25f);
        Vector3 myPos = initialPos;
        GameObject tempfloor;
        //building floor x-axis
     /*    for(int i = 0; i < ySize; i++){
            for(int j = 0; j <= xSize; j++){
                myPos = new Vector3 (initialPos.x + (j*wallLength) - wallLength,-0.5f,2*initialPos.z + (i*wallLength) - wallLength/2);
                tempfloor = Instantiate(Floor,myPos,Quaternion.identity) as GameObject;
                tempfloor.transform.parent = floorHolder.transform;
            }
        }
                myPos = new Vector3 (-0.1f,-0.5f,-0.5f);
                tempfloor = Instantiate(Floor,myPos,Quaternion.Euler(0.0f,90,0.0f)) as GameObject;
                tempfloor.transform.parent = floorHolder.transform;
    }*/
    void SpawnCheese()
    {
        CheeseHolder = new GameObject();
        CheeseHolder.name = "Cheese ";
        int cheesCount = 0;
        Vector3 cheesePos = new Vector3(0, 0, 0);
        GameObject tempCheese;
        bool hitWall = true;
        int cellCount = 0;
        RaycastHit hit;
        while (cheesCount < 10)
        {
            float pos1 = UnityEngine.Random.Range(-15f, 15f);
           // float pos2 = UnityEngine.Random.Range(0f, 15f);
            float pos3 = UnityEngine.Random.Range(-15f, 15f);

            cheesePos = new Vector3(pos1,0.1f,pos3);
            while (cellCount < 1)
            {
                if(Physics.SphereCast(cheesePos, .1f, transform.forward, out hit, 1))
                {
                    Debug.Log("hit a wall");
                    cellCount++;
                }
                else
                {
                    hitWall = false;
                    cellCount++;
                }
            }
            tempCheese = Instantiate(Cheese, cheesePos, Quaternion.Euler(0.0f, 90, 0.0f)) as GameObject;
            tempCheese.transform.parent = CheeseHolder.transform;
            Debug.Log("cheese out");
            Debug.Log(cheesCount);
            cheesCount++;
            hitWall = true;
            cellCount = 0;
        }
      /*  cheesePos = new Vector3(-13.5f, .1f, -14.5f);
        tempCheese = Instantiate(Cheese, cheesePos, Quaternion.Euler(0.0f, 90, 0.0f)) as GameObject;
        tempCheese.transform.parent = CheeseHolder.transform;
        Debug.Log(tempCheese.name);
        */
    }

}
