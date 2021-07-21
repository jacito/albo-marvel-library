/*
 * 
 * Jazmín Velázquez Bustos 2021. All Rights Reserved.
 * JACITO 2021. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of JACITO
 * ("Confidential Information"). You shall not disclose such Confidential 
 * Information and shall use it only in accordance with the terms of the license 
 * agreement you entered into with  Jazmín V.B or his authorized licensees. 
 * 
 * JACITO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE 
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR 
 * NON-INFRINGEMENT. JACITO SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING,MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 *
 */
package albo.comics.library.model;

import com.albo.library.json.comics.Item__1;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jacito
 */
@Entity
@Table(name = "characters_relationship")
@NamedQueries({
    @NamedQuery(name = "CharactersRelationship.findAll", query = "SELECT c FROM CharactersRelationship c")
    , @NamedQuery(name = "CharactersRelationship.findById", query = "SELECT c FROM CharactersRelationship c WHERE c.id = :id")
    , @NamedQuery(name = "CharactersRelationship.findByName", query = "SELECT c FROM CharactersRelationship c WHERE c.name = :name")
    , @NamedQuery(name = "CharactersRelationship.findByMarvelId", query = "SELECT c FROM CharactersRelationship c WHERE c.marvelId = :marvelId")
    , @NamedQuery(name = "CharactersRelationship.findByComicId", query = "SELECT c FROM CharactersRelationship c WHERE c.comicId = :comicId")
    , @NamedQuery(name = "CharactersRelationship.findByCharacterPrincipalId", query = "SELECT c FROM CharactersRelationship c WHERE c.characterPrincipalId = :characterPrincipalId")
    , @NamedQuery(name = "CharactersRelationship.findByAll",
            query = "SELECT c FROM CharactersRelationship c WHERE c.characterPrincipalId = :characterPrincipalId AND c.name = :name AND c.marvelId = :marvelId AND c.comicId = :comicId")})
public class CharactersRelationship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "marvel_id")
    private int marvelId;

    @JoinColumn(name = "character_principal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CharacterPrincipal characterPrincipalId;
    @JoinColumn(name = "comic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comic comicId;

    public CharactersRelationship() {
    }

    public CharactersRelationship(Integer id) {
        this.id = id;
    }

    public CharactersRelationship(Item__1 character, CharacterPrincipal characterPrincipalId, Comic comicId) {
        this.name = character.getName();
        this.marvelId = Integer.parseInt(character.getResourceURI().substring(character.getResourceURI().lastIndexOf("/") + 1));
        this.characterPrincipalId = characterPrincipalId;
        this.comicId = comicId;
    }

    public CharactersRelationship load(CharactersRelationship charactersRelationship) {
        this.name = charactersRelationship.getName();
        this.marvelId = charactersRelationship.getMarvelId();
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarvelId() {
        return marvelId;
    }

    public void setMarvelId(int marvelId) {
        this.marvelId = marvelId;
    }

    public CharacterPrincipal getCharacterPrincipalId() {
        return characterPrincipalId;
    }

    public void setCharacterPrincipalId(CharacterPrincipal characterPrincipalId) {
        this.characterPrincipalId = characterPrincipalId;
    }

    public Comic getComicId() {
        return comicId;
    }

    public void setComicId(Comic comicId) {
        this.comicId = comicId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharactersRelationship)) {
            return false;
        }
        CharactersRelationship other = (CharactersRelationship) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CharactersRelationship{"
                + "id=" + id + ", "
                + "name=" + name + ", "
                + "marvelId=" + marvelId + ", "
                + "characterPrincipalId=[" + ((characterPrincipalId == null)
                        ? "NULL" : (characterPrincipalId.getId() + "- " + characterPrincipalId.getName())) + "], "
                + "comicId=[" + ((comicId == null) ? "NULL" : (comicId.getId() + "-" + comicId.getMarvelId())) + "]}";
    }

}
