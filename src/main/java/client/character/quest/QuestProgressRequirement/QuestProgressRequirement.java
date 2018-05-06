package client.character.quest.QuestProgressRequirement;

import loaders.DatSerializable;

import javax.persistence.*;

/**
 * Created on 3/2/2018.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "questProgressRequirements")
@DiscriminatorColumn(name = "progressType")
public abstract class QuestProgressRequirement implements DatSerializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Returns whether this progress requirement has been completed by the player.
     * @return Completeness.
     */
    public abstract boolean isComplete();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
