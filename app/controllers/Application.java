package controllers;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import models.Pessoa;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.F;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {


    private Form<Pessoa> pessoaForm = Form.form(Pessoa.class);
    public Result index() {
        return ok(exemplo.render(pessoaForm));
    }
    
    public F.Promise<Result> cadastrar(){
        pessoaForm = Form.form(Pessoa.class).bindFromRequest();
        if(pessoaForm.hasErrors()){
            return F.Promise.promise(() ->badRequest(exemplo.render(pessoaForm)));
        }else{
            Pessoa pessoa = pessoaForm.get();
            TwilioRestClient twilioRestClient = new TwilioRestClient(Play.application().configuration().getString("twilioCredencials.accountSID"),
                    Play.application().configuration().getString("twilioCredencials.authToken"));

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("Body", "Bom dia amor, to sem credito entao to testando essa aplicação pra te deseja um bom diaaaaa"));
            params.add(new BasicNameValuePair("To", "+55"  + pessoa.telefone ));
            params.add(new BasicNameValuePair("From", "+14155992671"));
            MessageFactory messageFactory = twilioRestClient.getAccount().getMessageFactory();
            Message message;
            try {
                message = messageFactory.create(params);
                System.out.println(message.getSid());
            } catch (TwilioRestException e) {
                Logger.error("Erro enviando SMS", e);
               e.printStackTrace();
            }
        }

        return F.Promise.promise(() -> redirect(routes.Application.index()));
    }

}
