import styles from './Toolbar.module.css';

export default function ToolbarOrder(props) {
    function add() {
        props.onAdd();
    }

    return (
        <div className="btn-group my-2 mx-4" role="group">
            <button type="button" className={`btn btn-success ${styles.btn}`} onClick={add}>
                Добавить
            </button>
        </div >
    );
}