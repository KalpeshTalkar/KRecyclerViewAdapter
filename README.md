# KGenericRecyclerAdapter

[![platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://raw.githubusercontent.com/KalpeshTalkar/KGenericRecyclerAdapter/master/LICENSE)
[![Language: JAVA](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![Twitter](https://img.shields.io/badge/Twitter-@kalpeshtalkar-blue.svg?style=flat)](https://twitter.com/kalpeshtalkar)

##### A generic recyclerview adapter for all your recycler views.
##### No need to create a custom adapter for every recyclerview.

## Dependency:
### Add the following in your root build.gradle at the end of repositories:

```GRADLE
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### Add the dependency in your app level gradle:

```GRADLE
implementation 'com.github.KalpeshTalkar:KRecyclerViewAdapter:1.0.0'
```

## Usage:
#### Extend your holder from `KRecyclerViewHolder` 
#### Refer the sample code below:
```Java
public class MyHolder extends KRecyclerViewHolder {

    private TextView titleLabel, descLabel;

    public MyHolder(View itemView) {
        super(itemView);
        titleLabel = (TextView) itemView.findViewById(R.id.titleLabel);
        descLabel = (TextView) itemView.findViewById(R.id.descLabel);
    }
    
    @Override
    protected void setSelected(@NonNull Context context, boolean selected) {
        super.setSelected(context, selected);
        // This method is called whenever the holder is selected/unselected.
        if (selected) {
            // Selected
        } else {
            // Unselected
        }
    }

    @Override
    protected void setData(@NonNull Context context, @NonNull Object itemObject) {
        super.setData(context, itemObject);
        // This method is called automatically by the adapter.
        // override this method and set your data here...
        // Check the type of itemObject
        if (itemObject instanceof MyObject) {
            MyObject myObject = (MyObject)itemObject;
            titleLabel.setText(myObject.title);
            descLabel.setText(myObject.description);
        }
    }
    
}
```

#### Set your adapter (Single View Type)
```Java
KRecyclerViewAdapter adapter = new KRecyclerViewAdapter(this, YOUR_ARRAY, new KRecyclerViewHolderCallBack() {
    @Override
    public KRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item, parent, false);
        return new MyHolder(layoutView);
    }

    @Override
    public void onHolderDisplayed(@NonNull KRecyclerViewHolder holder, int position) {
        Log.i("onHolderDisplayed", "Holder Displayed At: " + position);
    }       
}, new KRecyclerViewItemClickListener() {
    @Override
    public void onRecyclerItemClicked(@NonNull KRecyclerViewHolder holder, @NonNull Object itemObject, int itemPosition) {
        Toast.makeText(MainActivity.this, "Clicked position " + itemPosition, Toast.LENGTH_SHORT).show();
    }
});

YOUR_RECYCLER_VIEW.setAdapter(adapter);
```
#### Other properties
```Java
adapter.allowsSingleSelection = true;           // Enables single selection
adapter.allowsMultipleSelection = true;         // Enables multiple selection
adapter.deselectItemOnClickIfSelected = true;   // Deselects the item if already selected.

adapter.getSelectedIndexes();                   // Get list of selected item positions
```

#### Set your adapter (Multiple View Type)
```Java
KRecyclerViewAdapter adapter = new KRecyclerViewAdapter(this, YOUR_ARRAY, new KRecyclerViewHolderViewTypeCallBack() {
    @Override
    public int recyclerItemViewType(int position, @NonNull Object itemObject) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public KRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item, null);
            return new SimpleHolder(layoutView);
        } else {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.another_item, null);
            return new AnotherHolder(layoutView);
        }
    }

    @Override
    public void onHolderDisplayed(@NonNull KRecyclerViewHolder holder, int position) {
        Log.i("onHolderDisplayed", "Holder Displayed At: " + position);
    }
}, new KRecyclerViewItemClickListener() {
    @Override
    public void onRecyclerItemClicked(@NonNull KRecyclerViewHolder holder, @NonNull Object itemObject, int itemPosition) {
        Toast.makeText(MainActivity.this, "Clicked position " + itemPosition, Toast.LENGTH_SHORT).show();
    }
});

YOUR_RECYCLER_VIEW.setAdapter(adapter);
```
