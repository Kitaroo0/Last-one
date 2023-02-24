import Data.PostgresDB;
import Data.interfaces.IDB;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import controrllers.UserController;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Date;

public class Main  {

    public static void main(String[] args) throws ClientException, ApiException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        GroupActor actor = new GroupActor(219016935, "vk1.a.qqJ8sHEA93rtqkTJARi9np1cHP5N8IzBKo8Ob_Xh8PepNXr3JWob7Lpx94vPpdIrfw74hmpYdgHKBlIAO0GOA7ZszWb95yQ3RyX51kcVifU5Chlae-yHEg_SayHvBll2imPHaMG_0Gwmk3tS3L-z2RMGYb825b1N_jai6wSF4TSw6rxK6Qrv3oLLtlXRkvTjU3zMuOrB7KZfchXsw17b4g");
        ContactForm form = new ContactForm();
        Scanner sc = new Scanner(System.in);
        IDB db = new PostgresDB();
        IUserRepository repo = new UserRepository(db);
        UserController controller = new UserController(repo);
        Appl app = new Appl(controller);
        while (true){
            System.out.print("1.Registration\n2.Log in\n3.Break\nSelect next step: ");
            int choice = sc.nextInt();
            if (choice==1)
            {
                //form.setVisible(true);
                app.startreg();
                Date date = new Date();
                vk.messages().send(actor).message("A new account has been registered: \""+  app.getLog() +"\". "+ date.toString()).userId(267720145).randomId(random.nextInt(10000)).execute();
                continue;
            } else if (choice==2) {
                app.startlog();
                Date date = new Date();
                vk.messages().send(actor).message("An \"" + app.getLog() +"\" account has been logged in. " + date.toString()).userId(267720145).randomId(random.nextInt(10000)).execute();
                break;
            }
            else if (choice==3){
                //System.out.println(form.getName_field());
                break;
            }
            else {System.out.print("Wrong input!\nTry again: ");
                continue;}
        }
    }
}