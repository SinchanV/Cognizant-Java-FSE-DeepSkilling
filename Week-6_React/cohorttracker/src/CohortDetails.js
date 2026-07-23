
import styles from './CohortDetails.module.css';

function CohortDetails(props) {
    // Determine the CSS class based on cohort status
    const statusClass = props.cohort.currentStatus.toLowerCase() === 'ongoing' 
        ? styles.ongoing 
        : props.cohort.currentStatus.toLowerCase() === 'scheduled' 
        ? styles.scheduled 
        : '';

    return (
        <div className={styles.box}>
            <h3 className={statusClass}>
                {props.cohort.cohortCode} -
                <span> {props.cohort.technology}</span>
            </h3>
            <dl>
                <dt>Started On</dt>
                <dd>{props.cohort.startDate}</dd>
                <dt>Current Status</dt>
                <dd>{props.cohort.currentStatus}</dd>
                <dt>Coach</dt>
                <dd>{props.cohort.coachName}</dd>
                <dt>Trainer</dt>
                <dd>{props.cohort.trainerName}</dd>
            </dl>
        </div>
    );
}
export default CohortDetails;