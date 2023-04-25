import { useState, useEffect } from "react";
import Modal from './Modal';
import DataService from '../../services/DataService';
import Toolbar from './Toolbar';
import Table from './Table';

export default function CustomerTable(props){
    const [items, setItems] = useState([]);
    const [modalHeader, setModalHeader] = useState('');
    const [modalConfirm, setModalConfirm] = useState('');
    const [modalVisible, setModalVisible] = useState(false);
    const [isEdit, setEdit] = useState(false);

    let selectedItems = [];

    useEffect(() => {
        loadItems();
    }, []);

    function loadItems() {
        DataService.readAll(props.getAllUrl, props.transformer)
            .then(data => setItems(data));
    }

    function saveItem() {
        if (!isEdit) {
            DataService.create(props.url, "?productName=" + props.data.productName).then(() => loadItems());
        } else {
            DataService.update(props.getUrl + props.data.id, "?productName=" + props.data.productName).then(() => loadItems());
        }
    }

    function handleAdd() {
        setEdit(false);
        setModalHeader('Добавления товара');
        setModalConfirm('Добавить');
        setModalVisible(true);
        props.onAdd();
    }

    function handleEdit() {
        if (selectedItems.length === 0) {
            return;
        }
        edit(selectedItems[0]);
    }

    function edit(editedId) {
        DataService.read(props.getUrl + editedId, props.transformer)
            .then(data => {
                setEdit(true);
                setModalHeader('Редактирование пользователя');
                setModalConfirm('Сохранить');
                setModalVisible(true);
                props.onEdit(data);
            });
    }

    function handleRemove() {
        if (selectedItems.length === 0) {
            return;
        }
        if (confirm('Удалить выбранные элементы?')) {
            const promises = [];
            selectedItems.forEach(item => {
                promises.push(DataService.delete(props.getUrl + item));
            });
            Promise.all(promises).then((results) => {
                selectedItems.length = 0;
                loadItems();
            });
        }
    }

    function handleTableClick(tableSelectedItems) {
        selectedItems = tableSelectedItems;
    }

    function handleTableDblClick(tableSelectedItem) {
        edit(tableSelectedItem);
    }

    function handleModalHide() {
        setModalVisible(false);
    }

    function handleModalDone() {
        saveItem();
    }

    return(
        <>
            <Toolbar 
                onAdd={handleAdd}
                onEdit={handleEdit}
                onRemove={handleRemove}/>
            <Table 
                headers={props.headers} 
                items={items}
                selectable={true}
                onClick={handleTableClick}
                onDblClick={handleTableDblClick}/>
            <Modal 
                header={modalHeader}
                confirm={modalConfirm}
                visible={modalVisible} 
                onHide={handleModalHide}
                onDone={handleModalDone}>
                    {props.children}
            </Modal>
        </>
    )
}