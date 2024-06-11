package com.example.android_client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;
    private MainActivity mainActivity;

    // Constructor
    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.nim.setText(user.getNim());
        holder.prodi.setText(user.getProdi());
        holder.semester.setText(user.getSemester());
        holder.email.setText(user.getEmail());

        // Item click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity != null) {
                    mainActivity.showUpdateDialog(user);
                }
            }
        });

        // Delete button click listener
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(user.getId(), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    // Show delete confirmation dialog
    private void showDeleteConfirmationDialog(final int userId, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Menghapus Data User");
        builder.setMessage("Apakah kamu yakin ingin menghapus data user ini?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser(userId, position);
            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
    }

    // Delete user method
    private void deleteUser(final int userId, final int position) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.deleteUser(userId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    userList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "User Berhasil Dihapus", Toast.LENGTH_SHORT).show();

                    if (mainActivity != null) {
                        mainActivity.refreshData();
                    }
                } else {
                    Toast.makeText(context, "Gagal Menghapus user: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Gagal Menghapus user: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ViewHolder for UserAdapter
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name, nim, prodi, semester, email;
        public Button buttonDelete;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            nim = itemView.findViewById(R.id.textViewNim);
            prodi = itemView.findViewById(R.id.textViewProdi);
            semester = itemView.findViewById(R.id.textViewSemester);
            email = itemView.findViewById(R.id.textViewEmail);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }

    // Set MainActivity method
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
