package com.example.meuteclado;

import android.inputmethodservice.InputMethodService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MyInputMethodService extends InputMethodService {

    private boolean isShifted = false;
    private boolean isNumericMode = false;
    private View alphabeticKeyboardView;
    private View numericKeyboardView;

    @Override
    public View onCreateInputView() {
        alphabeticKeyboardView = LayoutInflater.from(this).inflate(R.layout.keyboard_layout, null);
        numericKeyboardView = LayoutInflater.from(this).inflate(R.layout.numeric_layout, null);

        setupAlphabeticKeyboard(alphabeticKeyboardView);
        setupNumericKeyboard(numericKeyboardView);

        return alphabeticKeyboardView;
    }

    private void setupAlphabeticKeyboard(View keyboardView) {

        //Primeira fileira
        setupKey(keyboardView, R.id.key_q, "q");
        setupKey(keyboardView, R.id.key_w, "w");
        setupKeyWithPopup(keyboardView, R.id.key_e, "e", new String[]{"é", "ê", "è"});
        setupKey(keyboardView, R.id.key_r, "r");
        setupKey(keyboardView, R.id.key_t, "t");
        setupKey(keyboardView, R.id.key_y, "y");
        setupKey(keyboardView, R.id.key_u, "u");
        setupKey(keyboardView, R.id.key_i, "i");
        setupKeyWithPopup(keyboardView, R.id.key_o, "o", new String[]{"ó", "ô", "ò"});
        setupKey(keyboardView, R.id.key_p, "p");

        //Segunda fileira
        setupKey(keyboardView, R.id.key_a, "a");
        setupKey(keyboardView, R.id.key_s, "s");
        setupKey(keyboardView, R.id.key_d, "d");
        setupKey(keyboardView, R.id.key_f, "f");
        setupKey(keyboardView, R.id.key_g, "g");
        setupKey(keyboardView, R.id.key_h, "h");
        setupKey(keyboardView, R.id.key_j, "j");
        setupKey(keyboardView, R.id.key_k, "k");
        setupKey(keyboardView, R.id.key_l, "l");
        setupKey(keyboardView, R.id.key_ç, "ç");

        //Terceira fileira
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
    }

    private void setupNumericKeyboard(View keyboardView) {
        setupKey(keyboardView, R.id.key_0, "0");
        setupKey(keyboardView, R.id.key_1, "1");
        setupKey(keyboardView, R.id.key_2, "2");
        setupKey(keyboardView, R.id.key_3, "3");
        setupKey(keyboardView, R.id.key_4, "4");
        setupKey(keyboardView, R.id.key_5, "5");
        setupKey(keyboardView, R.id.key_6, "6");
        setupKey(keyboardView, R.id.key_7, "7");
        setupKey(keyboardView, R.id.key_8, "8");
        setupKey(keyboardView, R.id.key_9, "9");

        setupKey(keyboardView, R.id.arroba, "@");
        setupKey(keyboardView, R.id.jogodavelha, "#");
        setupKey(keyboardView, R.id.cifrao, "$");
        setupKey(keyboardView, R.id.underline, "_");
        setupKey(keyboardView, R.id.ecomercial, "&");
        setupKey(keyboardView, R.id.menos, "-");
        setupKey(keyboardView, R.id.mais, "+");
        setupKey(keyboardView, R.id.parenteses1, "(");
        setupKey(keyboardView, R.id.parenteses2, ")");
        setupKey(keyboardView, R.id.barra, "/");

        setupKey(keyboardView, R.id.asterisco, "*");
        setupKey(keyboardView, R.id.aspasduplas, "\"");
        setupKey(keyboardView, R.id.asplassimples, "'");
        setupKey(keyboardView, R.id.doispontos, ":");
        setupKey(keyboardView, R.id.pontoevirgula, ";");
        setupKey(keyboardView, R.id.exclamacao, "!");
        setupKey(keyboardView, R.id.interrogacao, "?");

        setupKey(keyboardView, R.id.virgula, ",");
        setupKey(keyboardView, R.id.ponto, ".");

        setupDeleteKey(keyboardView);
        setupShiftKey(keyboardView);
        setupSpaceKey(keyboardView);
        setupDoneKey(keyboardView);

        Button alphaSwitchButton = keyboardView.findViewById(R.id.alfabeto);
        alphaSwitchButton.setOnClickListener(v -> switchToAlphabeticKeyboard());
    }

    private void setupKeyWithPopup(View keyboardView, int buttonId, String text, String[] popupOptions) {
        Button button = keyboardView.findViewById(buttonId);
        button.setOnClickListener(v -> {
            InputConnection inputConnection = getCurrentInputConnection();
            if (inputConnection != null) {
                String finalText = isShifted ? text.toUpperCase() : text.toLowerCase();
                inputConnection.commitText(finalText, 1);
            }
        });

        button.setOnLongClickListener(v -> {
            showPopupOptions(v, popupOptions);
            return true;
        });
    }

    private void showPopupOptions(View anchorView, String[] options) {
        LinearLayout popupLayout = new LinearLayout(this);
        popupLayout.setOrientation(LinearLayout.HORIZONTAL);

        PopupWindow popupWindow = new PopupWindow(popupLayout, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (String option : options) {
            Button optionButton = new Button(this);
            optionButton.setText(option);
            optionButton.setOnClickListener(v -> {
                InputConnection inputConnection = getCurrentInputConnection();
                if (inputConnection != null) {
                    inputConnection.commitText(option, 1);
                }
                popupWindow.dismiss();
            });
            popupLayout.addView(optionButton);
        }

        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(anchorView, 0, -anchorView.getHeight());
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
            isShifted = !isShifted;
        });
    }

    private void setupNumericKeyButton(View keyboardView) {
        Button numericButton = keyboardView.findViewById(R.id.numeros);
        numericButton.setOnClickListener(v -> switchToNumericKeyboard());
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
                inputConnection.commitText("\n", 1);
            }
        });
    }

    private void switchToNumericKeyboard() {
        isNumericMode = true;
        setInputView(numericKeyboardView);
    }

    private void switchToAlphabeticKeyboard() {
        isNumericMode = false;
        setInputView(alphabeticKeyboardView);
    }
}