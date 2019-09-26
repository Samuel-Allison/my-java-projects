using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerSpawn : MonoBehaviour
{
    public GameObject player;
    private GameObject playerHolder;

    private int totalcheese = 0;
    //public Text countText;



    // Start is called before the first frame update
    void Start()
    {
        spawnPlayer();
    }

    // Update is called once per frame

    private void spawnPlayer()
    {
        playerHolder = new GameObject();
        playerHolder.name = "Player1";
        GameObject tempPlay;
        Vector3 pos = new Vector3(14.6f, 0.0f, 13.9f);
        tempPlay = Instantiate(player, pos, Quaternion.identity) as GameObject;
        tempPlay.transform.parent = playerHolder.transform;
    }
}
