package com.example.whatthefeast


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spaceNavigationView: SpaceNavigationView = findViewById(R.id.space)
        val homeFragment = Home()
        val favoritesFragment = Favorites()
        val inventoryFragment = Inventory()
        val searchFragment = Search()
        val reciever = DataReceiver()

        spaceNavigationView.setCentreButtonSelectable(true)
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState)
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_storage_black_24dp))
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_favorite_black_24dp))
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_search_black_24dp))
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_settings_black_24dp))

        spaceNavigationView.setCentreButtonSelected()


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, homeFragment)
            commit()
        }



        spaceNavigationView.setSpaceOnClickListener(object : SpaceOnClickListener {
            override fun onCentreButtonClick() {
                Toast.makeText(this@MainActivity, "onCentreButtonClick", Toast.LENGTH_SHORT).show()
                spaceNavigationView.setCentreButtonSelectable(true)
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, homeFragment)
                    commit()
                }
            }

            override fun onItemClick(itemIndex: Int, itemName: String) {
                val fragment: Fragment = when (itemIndex) {
                    0 -> inventoryFragment
                    1 -> favoritesFragment
                    2 -> searchFragment
                    else -> {
                        homeFragment
                    }
                }
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, fragment  )
                    commit()
                }
            }

            override fun onItemReselected(itemIndex: Int, itemName: String) {
                Toast.makeText(
                    this@MainActivity,
                    "$itemIndex $itemName",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}
