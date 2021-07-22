package albo.comics.library.model;

import albo.comics.library.model.CharacterPrincipal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-21T20:53:18")
@StaticMetamodel(CharacterMarvel.class)
public class CharacterMarvel_ { 

    public static volatile SingularAttribute<CharacterMarvel, CharacterPrincipal> characterPrincipalId;
    public static volatile SingularAttribute<CharacterMarvel, String> marvelName;
    public static volatile SingularAttribute<CharacterMarvel, Date> marvelDateModified;
    public static volatile SingularAttribute<CharacterMarvel, String> marvelDescription;
    public static volatile SingularAttribute<CharacterMarvel, Integer> marvelId;
    public static volatile SingularAttribute<CharacterMarvel, Integer> id;
    public static volatile SingularAttribute<CharacterMarvel, Integer> marvelComicsAvailable;

}