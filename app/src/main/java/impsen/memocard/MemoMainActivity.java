package impsen.memocard;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoMainActivity  extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<MemoCard>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_main);
        expandableListView = (ExpandableListView) findViewById(R.id.CardList);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        expandableListAdapter = new MemoListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(expandableListAdapter);
        prepareListData();
        ((BaseExpandableListAdapter)expandableListAdapter).notifyDataSetChanged();
    }

    private void prepareListData() {
        String label = "Memo";
        listDataHeader.add(label);
        List<MemoCard>  memoCards = new ArrayList<>();
        for(Profile profile : Utils.loadProfiles(this.getApplicationContext())){
            memoCards.add(new MemoCard(this, profile));
        }
        listDataChild.put(label, memoCards);
    }

}
