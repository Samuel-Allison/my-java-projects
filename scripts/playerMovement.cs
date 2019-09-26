using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class playerMovement : MonoBehaviour
{
    private float rotationSpeed = 40.0f;
    private float dist = 1;
    private int totalcheese = 0;
    public Text Count;
    private bool mouseLook = false;

    public GameObject player;
    // Start is called before the first frame update
    void Start()
    {
        totalcheese = 0;
        Count.text = "Count: " + totalcheese.ToString();
        
        CheeseCount();
    }

    void FixedUpdate()
    {
        Debug.Log("Updating");
        //Debug.Log(GameObject.Find("player").transform.position);
        if (Input.GetKey("d"))
        {
            this.transform.position = this.transform.position + Camera.main.transform.right * dist * Time.deltaTime;
          //  this.transform.position = this.transform.position + Camera.main.gameObject.transform.forward * dist * Time.deltaTime;
        }
        if (Input.GetKey("a"))
        {
          
            this.transform.position = this.transform.position - Camera.main.transform.right * dist * Time.deltaTime;
        }
        if (Input.GetKey("w"))
        {
            this.transform.position = this.transform.position + Camera.main.transform.forward * dist * Time.deltaTime;

        }
        if (Input.GetKey("s"))
        {
            
            this.transform.position = this.transform.position - Camera.main.transform.forward * dist * Time.deltaTime;

        }
        if (Input.GetKey("q"))
        {
            this.transform.Rotate(-Vector3.up * rotationSpeed * Time.deltaTime);
        }
        if (Input.GetKey("e"))
        {
            this.transform.Rotate(Vector3.up * rotationSpeed * Time.deltaTime);
        }

        if (Input.GetKey("l"))
        {
            if (mouseLook == true)
            {
                mouseLook = false;
                Debug.Log("disable mouse look");
            }
            if (mouseLook == false)
            {
                mouseLook = true;
                Debug.Log("allow mouse look");
            }
            
        }
        if(mouseLook == true)
        {
            if (Input.GetAxis("Mouse X") > 0)
            {
                Debug.Log("should do something");
                this.transform.Rotate(Vector3.up * rotationSpeed * Time.deltaTime);
            }
            if (Input.GetAxis("Mouse X") < 0)
            {
                this.transform.Rotate(-Vector3.up * rotationSpeed * Time.deltaTime);
            }
        }


    }
    void OnCollisionEnter(Collision col)
    {
        Debug.Log("hit some thing");

        if (col.gameObject.name == "finish")
        {
            Destroy(col.gameObject);
            //end();
        }
        if (col.gameObject.name == "Cheese(Clone)")
        {
            Destroy(col.gameObject);
            totalcheese++;
            CheeseCount();
        }

        if (col.gameObject.name == "Maze")
        {
            Debug.Log("hit something");
            Debug.Log(col.collider.name);
            Destroy(col.gameObject);
        }
        if(col.gameObject.name == "Wall(Clone)")
        {
            Debug.Log("hit something");
            Debug.Log(col.collider.name);
          //  Destroy(col.gameObject);
        }
        if(col.gameObject.name == "Finish")
        {
            if(totalcheese == 3)
            {
                Destroy(col.gameObject);
            }
            //Debug.Log(col.collider.name);
            
        }

    }
    void CheeseCount()
    {
        Count.text = "Total Cheese: " + totalcheese.ToString();
    }
}
