package uk.gov.dwp.esf.mi.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QParticipant is a Querydsl query type for Participant
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParticipant extends EntityPathBase<Participant> {

    private static final long serialVersionUID = -498436357L;

    public static final QParticipant participant = new QParticipant("participant");

    public final BooleanPath alcoholUser = createBoolean("alcoholUser");

    public final BooleanPath basicSkills = createBoolean("basicSkills");

    public final NumberPath<Integer> contractId = createNumber("contractId", Integer.class);

    public final StringPath cor = createString("cor");

    public final DatePath<java.time.LocalDate> creationDate = createDate("creationDate", java.time.LocalDate.class);

    public final StringPath deliveryPostcode = createString("deliveryPostcode");

    public final BooleanPath disabled = createBoolean("disabled");

    public final DatePath<java.time.LocalDate> dob = createDate("dob", java.time.LocalDate.class);

    public final BooleanPath drugUser = createBoolean("drugUser");

    public final EnumPath<uk.gov.dwp.esf.mi.common.EntryEmpStatus> entryEmpStatus = createEnum("entryEmpStatus", uk.gov.dwp.esf.mi.common.EntryEmpStatus.class);

    public final EnumPath<uk.gov.dwp.esf.mi.common.Ethnicity> ethnicity = createEnum("ethnicity", uk.gov.dwp.esf.mi.common.Ethnicity.class);

    public final BooleanPath exitChildcare = createBoolean("exitChildcare");

    public final DatePath<java.time.LocalDate> exitDate = createDate("exitDate", java.time.LocalDate.class);

    public final EnumPath<uk.gov.dwp.esf.mi.common.ExitEmpStatus> exitEmpStatus = createEnum("exitEmpStatus", uk.gov.dwp.esf.mi.common.ExitEmpStatus.class);

    public final BooleanPath exitQualification = createBoolean("exitQualification");

    public final BooleanPath exitSkills = createBoolean("exitSkills");

    public final BooleanPath exitTraining = createBoolean("exitTraining");

    public final StringPath fundingAggrement = createString("fundingAggrement");

    public final StringPath gender = createString("gender");

    public final BooleanPath homeless = createBoolean("homeless");

    public final EnumPath<uk.gov.dwp.esf.mi.common.HouseHoldType> householdType = createEnum("householdType", uk.gov.dwp.esf.mi.common.HouseHoldType.class);

    public final EnumPath<uk.gov.dwp.esf.mi.common.ISCEDLevel> ISCEDLevel = createEnum("ISCEDLevel", uk.gov.dwp.esf.mi.common.ISCEDLevel.class);

    public final EnumPath<uk.gov.dwp.esf.mi.common.ISCEDLevel> iscedLevel = createEnum("iscedLevel", uk.gov.dwp.esf.mi.common.ISCEDLevel.class);

    public final BooleanPath longTermUnemployed = createBoolean("longTermUnemployed");

    public final BooleanPath match = createBoolean("match");

    public final StringPath nino = createString("nino");

    public final StringPath participantId = createString("participantId");

    public final StringPath priorityAxis = createString("priorityAxis");

    public final DatePath<java.time.LocalDate> proposedExitDate = createDate("proposedExitDate", java.time.LocalDate.class);

    public final NumberPath<Integer> providerId = createNumber("providerId", Integer.class);

    public final StringPath providerRef = createString("providerRef");

    public final EnumPath<uk.gov.dwp.esf.mi.common.RecordState> recordState = createEnum("recordState", uk.gov.dwp.esf.mi.common.RecordState.class);

    public final StringPath signedForm = createString("signedForm");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> updatedDate = createDate("updatedDate", java.time.LocalDate.class);

    public QParticipant(String variable) {
        super(Participant.class, forVariable(variable));
    }

    public QParticipant(Path<? extends Participant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParticipant(PathMetadata<?> metadata) {
        super(Participant.class, metadata);
    }

}

