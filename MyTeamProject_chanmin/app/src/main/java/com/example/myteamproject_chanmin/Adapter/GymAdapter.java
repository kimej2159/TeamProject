package com.example.myteamproject_chanmin.Adapter;

import static com.example.myteamproject_chanmin.Common.CommonMethod.ipConfig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myteamproject_chanmin.CenterDetail;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.GymDTO;
import com.example.myteamproject_chanmin.DTO.MemberDTO;
import com.example.myteamproject_chanmin.MainActivity;
import com.example.myteamproject_chanmin.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GymAdapter  extends
        RecyclerView.Adapter<GymAdapter.ViewHolder>{
    private static final String TAG = "main:SingerAdapter";

    TextView name_text , telephone_text;



    // 메인에게 넘겨받는것 -> 반드시 : Context, ArrayList<DTO>
    Context context;
    ArrayList<GymDTO> dtos;

    LayoutInflater inflater;

    // 생성자로 메인에서 넘겨받은것들을 연결
    public GymAdapter(Context context, ArrayList<GymDTO> dtos) {
        this.context = context;
        this.dtos = dtos;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);

    }

    ////////////////////////////////////////////////////
    // 매소드는 여기에 만든다
    // dtos에 dto를 추가하는 매소드
    public void addDto(GymDTO dto){
        dtos.add(dto);
    }

    // dtos의 특정 위치에 있는 dto를 삭제하는 매소드
    public void delDto(int position){
        dtos.remove(position);
    }

    ////////////////////////////////////////////////////

    // 화면을 인플레이트 시킨다 (붙인다)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.gymview,
                parent, false);

        name_text = itemView.findViewById(R.id.tvCenterName);
        telephone_text = itemView.findViewById(R.id.tvTelephoneNumber);

        return new ViewHolder(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        GymDTO dto = dtos.get(position);
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를
        // 사용하여 데이터를 셋팅시킨다
        holder.setDto(dto);

       /* holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int mPosition = holder.getAdapterPosition();

                //Context context = v.getContext();

                //CenterDetail centerDetail = new CenterDetail();

                //centerDetail.getActivity().findViewById(R.id.tvCenterName);
                //dtos.get(mPosition).getGym_id(); //아이디

            }
        });*/

        // 리싸이클러뷰에 항목을 선택했을때 그 항목을 가져오는 리스너
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymDTO dto1 = dtos.get(position);
                Toast.makeText(context,
                        "이름 : " + dto1.getGym_name(), Toast.LENGTH_SHORT).show();
                MainActivity mainActivity = new MainActivity();
                CenterDetail centerDetail = new CenterDetail();
                mainActivity.getSupportFragmentManager().beginTransaction().replace( , centerDetail).commit();


            }
        });



    }

    // dtos에 저장된 dto 갯수
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 3. xml 파일에서 사용된 모든 변수를 adapter에서 클래스로 선언한다
    public class ViewHolder extends RecyclerView.ViewHolder{
        // memberview.xml 에서 사용된 모든 위젯을 정의한다
        TextView tvCenterName, tvTelephoneNumber ,tvAddress;
        ImageView ivImage;
        LinearLayout parentLayout;

        // singerview.xml에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            tvCenterName = itemView.findViewById(R.id.tvCenterName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvTelephoneNumber = itemView.findViewById(R.id.tvTelephoneNumber);
            ivImage = itemView.findViewById(R.id.ivImage);

        }

        // singerview에 데이터를 연결시키는 매소드를 만든다
        public void setDto(GymDTO dto){
            tvCenterName.setText(dto.getGym_name());
            tvAddress.setText(dto.getAddress());
            tvTelephoneNumber.setText(dto.getTelephone_number());
            Glide.with(itemView).load(dto.getGym_picture())
                    .circleCrop()
                    .placeholder(R.drawable.face)
                    .into(ivImage);

            //ivImage.setImageResource(dto.getProfile());
        }

    }






}
