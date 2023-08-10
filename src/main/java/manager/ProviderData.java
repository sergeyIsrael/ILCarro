package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {

    @DataProvider
//    этот метод userDto() кладём в тест
    public Iterator<Object[]> userDto(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{new User()
                .withEmail("man@fgh.com")
                .withPassword("$Asdf0077")
                .withName("John")
                .withLastName("Silver")
        });
        list.add(new Object[]{new User()
                .withEmail("001@gmail.com")
                .withPassword("$As100500")
        });
        list.add(new Object[]{new User()
                .withEmail("002@mail.com")
                .withPassword("$Asdf00007")
        });
        return list.iterator();
    }

}
