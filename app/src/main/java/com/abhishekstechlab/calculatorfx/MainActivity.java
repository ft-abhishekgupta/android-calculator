package com.abhishekstechlab.calculatorfx;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Button b[];
    TextView tf, hi;
    String str = "";
    int op = 0;
    private String m_Text = "";
    ScrollView scv, scv2;
    boolean e = false, f = false, st = false;
    Animation anim;
    SharedPreferences value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        scv = (ScrollView) findViewById(R.id.scv);
        scv2 = (ScrollView) findViewById(R.id.scv2);
        int i = 0;
        String s[] = { "^", "E", "DEL", "CLR", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "x", ".", "0",
                "=", "/" };
        b = initializeButtons(20);
        for (i = 0; i < 20; i++) {
            b[i].setText(s[i]);
            b[i].setOnClickListener(this);
            b[i].setHapticFeedbackEnabled(true);
        }
        tf = (TextView) findViewById(R.id.textView2);
        hi = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public Button[] initializeButtons(int x) {
        Resources res = getResources();
        Button[] buttons = new Button[x];
        for (int i = 0; i < x; i++) {
            String b = "button" + (i + 1);
            buttons[i] = (Button) findViewById(res.getIdentifier(b, "id", getPackageName()));
        }
        return buttons;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.e) {
            str += 2.7182818284590452;
            tf.setText(str);
        } else if (item.getItemId() == R.id.sqrt) {
            op = 1;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.sin) {
            op = 2;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.cos) {
            op = 3;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.tan) {
            op = 4;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.log) {
            op = 5;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.ln) {
            op = 6;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.fact) {
            op = 7;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.pi) {
            DecimalFormat df = new DecimalFormat("#.########");
            str += df.format(Math.PI);
            tf.setText(str);
        } else if (item.getItemId() == R.id.rate) {
            Toast.makeText(this, "Thankyou", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                    | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        } else if (item.getItemId() == R.id.dev) {
            Toast.makeText(this, "Devoloped by Abhishek Gupta", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit) {
            System.exit(0);
        } else if (item.getItemId() == R.id.asin) {
            op = 8;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.acos) {
            op = 9;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.atan) {
            op = 10;
            mdialog(item.getTitle().toString());
        } else if (item.getItemId() == R.id.store) {
            if (st == false)
                Toast.makeText(this, "Press = , then Store", Toast.LENGTH_SHORT).show();
            else if (str.equals("Error"))
                Toast.makeText(this, "Press Clear", Toast.LENGTH_SHORT).show();
            else {
                value = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = value.edit();
                editor.putString("v", str);
                editor.commit();
                Toast.makeText(this, "Value Stored", Toast.LENGTH_SHORT).show();
            }
        } else if (item.getItemId() == R.id.recall) {
            value = getPreferences(Context.MODE_PRIVATE);
            str += (value.getString("v", ""));
            tf.setText(str);
            Toast.makeText(this, "Value Recalled", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.radian) {
            str += Math.toDegrees(1);
            tf.setText(str);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mdialog(String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle(title);
        final EditText input = new EditText(this);
        input.setInputType(
                InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        input.setTextColor(Color.WHITE);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                cal();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = "";
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void cal() {
        DecimalFormat df = new DecimalFormat("#.##########");
        if (!m_Text.equals("")) {
            try {
                String ans = "", temp = "";
                int kk = m_Text.indexOf('.');
                if (kk != -1)
                    temp = m_Text.substring(0, kk);
                else
                    temp = m_Text;

                BigInteger bigInt = new BigInteger(temp + "");
                if (bigInt.compareTo(BigInteger.valueOf((long) (Double.MAX_VALUE))) > 0) {
                    throw new Exception("e");
                }
                if (op == 1)
                    ans = df.format(Math.sqrt(Double.parseDouble(m_Text)));
                if (op == 2)
                    ans = df.format(Math.sin(Math.toRadians(Double.parseDouble(m_Text))));
                if (op == 3)
                    ans = df.format(Math.cos(Math.toRadians(Double.parseDouble(m_Text))));
                if (op == 4) {
                    if ((Double.parseDouble(m_Text) / 90) % 2 == 1.0) {
                        throw new Exception("test");
                    } else
                        ans = df.format(Math.tan(Math.toRadians(Double.parseDouble(m_Text))));
                }
                if (op == 5)
                    ans = df.format(Math.log10(Double.parseDouble(m_Text)));
                if (op == 6)
                    ans = df.format(Math.log(Double.parseDouble(m_Text)));
                if (op == 7) {
                    int n = Integer.parseInt(m_Text);
                    if (n > 100)
                        throw new Exception("e");
                    BigInteger fact = BigInteger.valueOf(1);
                    for (int i = 1; i <= n; i++)
                        fact = fact.multiply(BigInteger.valueOf(i));
                    ans = fact.doubleValue() + "";
                }
                if (op == 8) {
                    if (Math.abs(Double.parseDouble(m_Text)) > 1)
                        throw new Exception("e");
                    ans = df.format(Math.toDegrees(Math.asin(Double.parseDouble(m_Text))));
                }
                if (op == 9) {
                    if (Math.abs(Double.parseDouble(m_Text)) > 1)
                        throw new Exception("e");
                    ans = df.format(Math.toDegrees(Math.acos(Double.parseDouble(m_Text))));
                }
                if (op == 10) {
                    ans = df.format(Math.toDegrees(Math.atan(Double.parseDouble(m_Text))));
                }
                if (str.length() > 0) {
                    char l = str.charAt(str.length() - 1);
                    Button cl = (Button) findViewById(R.id.button4);
                    if (l == '0' || l == '1' || l == '2' || l == '3' || l == '4' || l == '5' || l == '6' || l == '7'
                            || l == '8' || l == '9')
                        cl.performClick();
                }
                if (ans.equals("-0"))
                    ans = "0";
                str = str + ans;
                f = true;
                tf.setText(str);
            } catch (Exception e) {
                str = "Error";
                tf.setText("Error");
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button19)
            st = true;
        else
            st = false;
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.press);
        v.startAnimation(anim);
        scv.fullScroll(View.FOCUS_DOWN);
        scv2.fullScroll(View.FOCUS_DOWN);
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        char c = ((Button) findViewById(v.getId())).getText().charAt(0);
        if (v.getId() == R.id.button4) {
            hi.setText(hi.getText().toString() + "\n" + "-> " + str);
            str = "";
            e = false;
            f = false;
        } else if (!tf.getText().equals("Error")) {
            if (v.getId() == R.id.button3) {
                if (str.length() > 0 && str != null) {
                    char cc = str.charAt(str.length() - 1);
                    if (cc == '+' || cc == '-' || cc == 'x' || cc == '/' || cc == '^')
                        e = false;
                    str = str.substring(0, str.length() - 1);
                }
            } else if (v.getId() == R.id.button19) {
                if (!str.equals("")) {
                    hi.setText(hi.getText().toString() + "\n" + "-> " + str);
                    str = calculate();
                    f = true;
                    e = false;
                }
            } else if (c == '+' || c == '-' || c == 'x' || c == '/' || c == '^') {
                f = false;
                if (str.equals("")) {
                } else if (str.charAt(str.length() - 1) == 'E') {
                } else {
                    if (e == false) {
                        e = true;
                    } else {
                        char t = str.charAt(str.length() - 1);
                        if (c != '-') {
                            hi.setText(hi.getText().toString() + "\n" + "-> " + str);
                            str = calculate();

                        } else if (t != '+' && t != '-' && t != 'x' && t != '/' && t != '^') {
                            hi.setText(hi.getText().toString() + "\n" + "-> " + str);
                            str = calculate();
                        }
                    }
                }
                if (!str.equals("Error")) {
                    str += c;
                } else {
                    hi.setText(hi.getText().toString() + c);
                }
            } else {
                if (f == true)
                    (findViewById(R.id.button4)).performClick();
                str += c;
            }
        }
        scv.fullScroll(View.FOCUS_DOWN);
        tf.setText(str);
    }

    public String calculate() {
        scv.fullScroll(View.FOCUS_DOWN);
        String tc = str, ans = "";
        char ch;
        int l = tc.length(), c = 1;
        double n1, n2, s = 0;
        try {
            while (c < l && (tc.charAt(c) - 48 >= 0 && tc.charAt(c) - 48 <= 9 || tc.charAt(c) == '.'
                    || tc.charAt(c) == 'E' || tc.charAt(c - 1) == 'E'))
                c++;
            if (c == l) {
                return tc;
            }
            n1 = Double.parseDouble(tc.substring(0, c));
            n2 = Double.parseDouble(tc.substring(c + 1, l));
            BigDecimal n11 = new BigDecimal(tc.substring(0, c));
            BigDecimal n22 = new BigDecimal(tc.substring(c + 1, l));
            BigDecimal anss = new BigDecimal("0");

            ch = tc.charAt(c);
            switch (ch) {
            case '+':
                anss = n11.add(n22);
                break;
            case '-':
                anss = n11.subtract(n22);
                break;
            case 'x':
                anss = n11.multiply(n22);
                break;
            case '/':
                anss = n11.divide(n22, 10, BigDecimal.ROUND_HALF_UP);
                break;
            case '^':
                anss = BigDecimal.valueOf(Math.pow(n1, n2));
                break;
            }
            return anss.doubleValue() + "";
        } catch (Exception e) {
            return "Error";
        }
    }
}
