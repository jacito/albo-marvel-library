package albo.comics.library.model;

import albo.comics.library.model.CharacterMarvel;
import albo.comics.library.model.CharactersRelationship;
import albo.comics.library.model.Comic;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-21T14:17:27")
@StaticMetamodel(CharacterPrincipal.class)
public class CharacterPrincipal_ { 

    public static volatile SingularAttribute<CharacterPrincipal, String> template;
    public static volatile SingularAttribute<CharacterPrincipal, String> marvelName;
    public static volatile ListAttribute<CharacterPrincipal, CharactersRelationship> charactersRelationshipList;
    public static volatile SingularAttribute<CharacterPrincipal, String> name;
    public static volatile SingularAttribute<CharacterPrincipal, Integer> id;
    public static volatile ListAttribute<CharacterPrincipal, Comic> comicList;
    public static volatile ListAttribute<CharacterPrincipal, CharacterMarvel> characterMarvelList;
    public static volatile SingularAttribute<CharacterPrincipal, Date> systemUpdateDate;

}