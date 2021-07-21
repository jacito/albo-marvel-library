package albo.comics.library.model;

import albo.comics.library.model.CharacterPrincipal;
import albo.comics.library.model.Comic;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-21T14:17:27")
@StaticMetamodel(CharactersRelationship.class)
public class CharactersRelationship_ { 

    public static volatile SingularAttribute<CharactersRelationship, CharacterPrincipal> characterPrincipalId;
    public static volatile SingularAttribute<CharactersRelationship, Comic> comicId;
    public static volatile SingularAttribute<CharactersRelationship, String> name;
    public static volatile SingularAttribute<CharactersRelationship, Integer> marvelId;
    public static volatile SingularAttribute<CharactersRelationship, Integer> id;

}