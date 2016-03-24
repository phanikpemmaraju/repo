package uk.gov.dwp.esf.mi.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProvider is a Querydsl query type for Provider
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProvider extends EntityPathBase<Provider> {

    private static final long serialVersionUID = 1824641833L;

    public static final QProvider provider = new QProvider("provider");

    public final StringPath email = createString("email");

    public final ListPath<Participant, QParticipant> participants = this.<Participant, QParticipant>createList("participants", Participant.class, QParticipant.class, PathInits.DIRECT2);

    public final StringPath postcode = createString("postcode");

    public final NumberPath<Integer> providerId = createNumber("providerId", Integer.class);

    public final StringPath providerName = createString("providerName");

    public QProvider(String variable) {
        super(Provider.class, forVariable(variable));
    }

    public QProvider(Path<? extends Provider> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProvider(PathMetadata<?> metadata) {
        super(Provider.class, metadata);
    }

}

