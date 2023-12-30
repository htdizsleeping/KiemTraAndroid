package com.example.kiemtraandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SanPhamAdapter sanPhamAdapter;
    private SanPhamDAO sanPhamDAO;
    TextView edtMaSP, edtTenSP, edtPrice, edtImageName;
    Button btnClear, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize SanPhamDAO
        sanPhamDAO = new SanPhamDAO(this);
        View customDialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_items, null);
        btnSave = customDialogView.findViewById(R.id.btnSave);
        edtMaSP = customDialogView.findViewById(R.id.edtMaSP);
        edtTenSP = customDialogView.findViewById(R.id.edtTenSP);
        edtPrice = customDialogView.findViewById(R.id.edtPrice);
        edtImageName = customDialogView.findViewById(R.id.edtImageName);

        // Get data from DAO and set up the adapter
        ArrayList<SanPham> listSanPham = sanPhamDAO.getListSanPham();
        sanPhamAdapter = new SanPhamAdapter(this, listSanPham, sanPhamDAO);
        recyclerView.setAdapter(sanPhamAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(sanPhamAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        FloatingActionButton flButton = findViewById(R.id.flButton);
        flButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View customDialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_items, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(customDialogView);

                // Find views in the AlertDialog
                EditText edtMaSPDialog = customDialogView.findViewById(R.id.edtMaSP);
                EditText edtTenSPDialog = customDialogView.findViewById(R.id.edtTenSP);
                EditText edtPriceDialog = customDialogView.findViewById(R.id.edtPrice);
                EditText edtImageNameDialog = customDialogView.findViewById(R.id.edtImageName);
                Button btnClearDialog = customDialogView.findViewById(R.id.btnClear);
                Button btnSaveDialog = customDialogView.findViewById(R.id.btnSave);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                // Handle Clear button in the AlertDialog
                btnClearDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtMaSPDialog.setText("");
                        edtTenSPDialog.setText("");
                        edtPriceDialog.setText("");
                        edtImageNameDialog.setText("");
                    }
                });

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        });
        // Other methods and logic for the activity can be added here
    }
}