package com.example.meuteclado;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class MyInputMethodService extends InputMethodService {

    private boolean isShifted = false;

    @Override
    public View onCreateInputView() {
        View keyboardView = LayoutInflater.from(this).inflate(R.layout.keyboard_layout, null);

        InputConnection inputConnection = getCurrentInputConnection();

        setupKey(keyboardView, R.id.key_q, "q");
        setupKey(keyboardView, R.id.key_w, "w");
        setupKey(keyboardView, R.id.key_e, "e");
        setupKey(keyboardView, R.id.key_r, "r");
        setupKey(keyboardView, R.id.key_t, "t");
        setupKey(keyboardView, R.id.key_y, "y");
        setupKey(keyboardView, R.id.key_u, "u");
        setupKey(keyboardView, R.id.key_i, "i");
        setupKey(keyboardView, R.id.key_o, "o");
        setupKey(keyboardView, R.id.key_p, "p");

        setupKey(keyboardView, R.id.key_a, "a");
        setupKey(keyboardView, R.id.key_s, "s");
        setupKey(keyboardView, R.id.key_d, "d");
        setupKey(keyboardView, R.id.key_f, "f");
        setupKey(keyboardView, R.id.key_g, "g");
        setupKey(keyboardView, R.id.key_h, "h");
        setupKey(keyboardView, R.id.key_j, "j");
        setupKey(keyboardView, R.id.key_k, "k");
        setupKey(keyboardView, R.id.key_l, "l");

        setupKey(keyboardView, R.id.key_z, "z");
        setupKey(keyboardView, R.id.key_x, "x");
        setupKey(keyboardView, R.id.key_c, "c");
        setupKey(keyboardView, R.id.key_v, "v");
        setupKey(keyboardView, R.id.key_b, "b");
        setupKey(keyboardView, R.id.key_n, "n");
        setupKey(keyboardView, R.id.key_m, "m");

        setupKey(keyboardView, R.id.virgula, ",");
        setupKey(keyboardView, R.id.ponto, ".");

        setupDeleteKey(keyboardView);
        setupShiftKey(keyboardView);
        setupNumericKeyButton(keyboardView);
        setupSpaceKey(keyboardView);
        setupDoneKey(keyboardView);

        return keyboardView;
    }

    private void setupKey(View keyboardView, int buttonId, String text) {
        Button button = keyboardView.findViewById(buttonId);
        button.setOnClickListener(v -> {
            InputConnection inputConnection = getCurrentInputConnection();
            if (inputConnection != null) {
                String finalText = isShifted ? text.toUpperCase() : text.toLowerCase();
                inputConnection.commitText(finalText, 1);
            }
        });
    }

    private void setupDeleteKey(View keyboardView) {
        Button deleteButton = keyboardView.findViewById(R.id.delete);
        deleteButton.setOnClickListener(v -> {
            InputConnection inputConnection = getCurrentInputConnection();
            if (inputConnection != null) {
                inputConnection.deleteSurroundingText(1, 0);
            }
        });
    }

    private void setupShiftKey(View keyboardView) {
        Button shiftButton = keyboardView.findViewById(R.id.shift);
        shiftButton.setOnClickListener(v -> {

            if (isShifted) {
                isShifted = false;
            } else {
                isShifted = true;
            }
        });
    }

    private void setupNumericKeyButton(View keyboardView) {
        Button numericButton = keyboardView.findViewById(R.id.numeros);
        numericButton.setOnClickListener(v -> {
            // Lógica para mudar para o teclado numérico
            //setNumericKeyboard();
        });
    }

    private void setupSpaceKey(View keyboardView) {
        Button spaceButton = keyboardView.findViewById(R.id.space);
        spaceButton.setOnClickListener(v -> {
            InputConnection inputConnection = getCurrentInputConnection();
            if (inputConnection != null) {
                inputConnection.commitText(" ", 1);
            }
        });
    }

    private void setupDoneKey(View keyboardView) {
        Button doneButton = keyboardView.findViewById(R.id.done);
        doneButton.setOnClickListener(v -> {
            InputConnection inputConnection = getCurrentInputConnection();
            if (inputConnection != null) {
                inputConnection.finishComposingText();
            }
        });
    }

}
