package com.example.meuteclado;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class MyInputMethodService extends InputMethodService {

    @Override
    public View onCreateInputView() {
        // Carrega o layout do teclado
        View keyboardView = LayoutInflater.from(this).inflate(R.layout.keyboard_layout, null);

        // Pega a conexão de input (o campo de texto onde o teclado está escrevendo)
        InputConnection inputConnection = getCurrentInputConnection();

        // Função para configurar cada botão de letra
        setupKey(keyboardView, R.id.key_q, "Q");
        setupKey(keyboardView, R.id.key_w, "W");
        setupKey(keyboardView, R.id.key_e, "E");
        setupKey(keyboardView, R.id.key_r, "R");
        setupKey(keyboardView, R.id.key_t, "T");
        setupKey(keyboardView, R.id.key_y, "Y");
        setupKey(keyboardView, R.id.key_u, "U");
        setupKey(keyboardView, R.id.key_i, "I");
        setupKey(keyboardView, R.id.key_o, "O");
        setupKey(keyboardView, R.id.key_p, "P");

        setupKey(keyboardView, R.id.key_a, "A");
        setupKey(keyboardView, R.id.key_s, "S");
        setupKey(keyboardView, R.id.key_d, "D");
        setupKey(keyboardView, R.id.key_f, "F");
        setupKey(keyboardView, R.id.key_g, "G");
        setupKey(keyboardView, R.id.key_h, "H");
        setupKey(keyboardView, R.id.key_j, "J");
        setupKey(keyboardView, R.id.key_k, "K");
        setupKey(keyboardView, R.id.key_l, "L");

        setupKey(keyboardView, R.id.key_z, "Z");
        setupKey(keyboardView, R.id.key_x, "X");
        setupKey(keyboardView, R.id.key_c, "C");
        setupKey(keyboardView, R.id.key_v, "V");
        setupKey(keyboardView, R.id.key_b, "B");
        setupKey(keyboardView, R.id.key_n, "N");
        setupKey(keyboardView, R.id.key_m, "M");

        return keyboardView;
    }

    private void setupKey(View keyboardView, int buttonId, String text) {
        Button button = keyboardView.findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Obter a conexão de entrada sempre que um botão é pressionado
            InputConnection inputConnection = getCurrentInputConnection();
            if (inputConnection != null) {
                inputConnection.commitText(text, 1);
            }
        });
    }
}
