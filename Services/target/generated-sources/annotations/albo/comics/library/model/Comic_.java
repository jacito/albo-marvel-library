package albo.comics.library.model;

import albo.comics.library.model.CharacterPrincipal;
import albo.comics.library.model.CharactersRelationship;
import albo.comics.library.model.Creator;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-21T10:37:53")
@StaticMetamodel(Comic.class)
public class Comic_ { 

    public static volatile SingularAttribute<Comic, CharacterPrincipal> characterPrincipalId;
    public static volatile SingularAttribute<Comic, String> marvelTitle;
    public static volatile SingularAttribute<Comic, String> marvelDateModified;
    public static volatile ListAttribute<Comic, Creator> creatorList;
    public static volatile ListAttribute<Comic, CharactersRelationship> charactersRelationshipList;
    public static volatile SingularAttribute<Comic, String> marvelDescription;
    public static volatile SingularAttribute<Comic, Integer> marvelId;
    public static volatile SingularAttribute<Comic, Integer> id;

}