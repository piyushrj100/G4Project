package g4eis.ontern.g4project;

/**
 * Created by piyush on 8/8/17.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import g4eis.ontern.g4project.R;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder> {

    private List<String> mItems;

    public RVAdapter(List<String> mItems) {
        this.mItems = mItems;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

       // private  TextView mTextView;
        private  TextView ques;
        //private  TextView ans;
       // private  CardView crd;



        public ItemViewHolder(View itemView) {
            super(itemView);
            ques = (TextView) itemView.findViewById(R.id.ques);
            //ans=(TextView) itemView.findViewById(R.id.ans);
            //crd= (CardView) itemView.findViewById(R.id.crd);


        }
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.ques.setText(mItems.get(i));
        //itemViewHolder.ans.setText(mItems.get(i));

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_dos, viewGroup, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}