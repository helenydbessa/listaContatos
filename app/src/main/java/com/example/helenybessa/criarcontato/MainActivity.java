package com.example.helenybessa.criarcontato;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button botao;
    EditText nome, tel, email, insta, face, linked;
    String name, phone, mail, gram, book, in;
    public final static int REQUEST_CODE_CONTACTS = 1, REQUEST_CODE_EMAIL = 2, REQUEST_CODE_WHATSAPP = 3, REQUEST_CODE_INSTAGRAM = 4, REQUEST_CODE_LINKEDIN = 5;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button)findViewById(R.id.button);

      //  nome = (EditText)findViewById(R.id.editNome);
    //    tel = (EditText)findViewById(R.id.editPhone);
      //  email = (EditText)findViewById(R.id.editEmail);

      //  name = nome.getText().toString();
      //  phone = tel.getText().toString();
      //  mail = email.getText().toString();

        botao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                nome = (EditText)findViewById(R.id.editNome);
                tel = (EditText)findViewById(R.id.editPhone);
                email = (EditText)findViewById(R.id.editEmail);
                insta = (EditText)findViewById(R.id.editIg);
                face = (EditText)findViewById(R.id.editFb);
                linked = (EditText)findViewById(R.id.editLin);

                name = nome.getText().toString();
                phone = tel.getText().toString();
                mail = email.getText().toString();
                gram = insta.getText().toString();
                book = face.getText().toString();
                in = linked.getText().toString();


                pessoa = lerDados(name, phone, mail, gram, book, in);

                Intent contato = new Intent(ContactsContract.Intents.Insert.ACTION);
                contato.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                contato.putExtra(ContactsContract.Intents.Insert.NAME, pessoa.getNome())
                .putExtra(ContactsContract.Intents.Insert.PHONE, pessoa.getPhone())
                .putExtra(ContactsContract.Intents.Insert.EMAIL, pessoa.getEmail());

                startActivityForResult(contato, 1);

                //openLinkedIn(pessoa.getLinkedin());
                //openInstagram(pessoa.getInstagram());
                //openFacebook(pessoa.getFacebook());
                //openWhatsappContact(pessoa.getPhone());
                //mandarEmail(pessoa.getEmail());


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_CODE_CONTACTS:
                mandarEmail(pessoa.getEmail());
                break;
            case REQUEST_CODE_EMAIL:
                openWhatsappContact(pessoa.getPhone());
                break;
            case REQUEST_CODE_WHATSAPP:
                openInstagram(pessoa.getInstagram());
                break;
            case REQUEST_CODE_INSTAGRAM:
                openLinkedIn(pessoa.getLinkedin());
                break;
            case REQUEST_CODE_LINKEDIN:
                goBackHome();
                break;
        }
    }

    private void goBackHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    private void openLinkedIn(String id) {
        String profile_url = "https://www.linkedin.com/in/" + id;
        try {
            Intent li = new Intent(Intent.ACTION_VIEW, Uri.parse(profile_url));
            startActivityForResult(li, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openInstagram(String username) {

        try {
            Uri uri = Uri.parse("http://instagram.com/_u/" + username);
            Intent ig = new Intent(Intent.ACTION_VIEW, uri);
            ig.setPackage("com.instagram.android");
            startActivityForResult(ig, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openWhatsappContact(String number) {

        try {
            Intent wapp = new Intent(Intent.ACTION_VIEW);
            wapp.setData(Uri.parse("http://api.whatsapp.com/send?phone=55" + number + "&text=" + "oie"));
            startActivityForResult(wapp, 3);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void mandarEmail(String e_mail) {
        String mailto = "mailto:" + e_mail;

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "minha lista de contatos");
        emailIntent.putExtra(Intent.EXTRA_TEXT   , "parabens foi adicionado palmas");
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivityForResult(emailIntent, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Pessoa lerDados(String n, String p, String e, String i, String f, String l) {
        Pessoa pessoa = new Pessoa(n, p, e, i, f, l);
        return pessoa;
    }

    private void inserirPessoa(Pessoa pessoa) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, pessoa.getNome());
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, pessoa.getEmail());
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, pessoa.getPhone());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
