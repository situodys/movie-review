import {useState} from "react";
import axios from "axios";
import {API_BASE_URL} from "./config/config";
import Image from "./Image";

function App() {

    const [files, setFiles] = useState([]);
    const [images, setImages] = useState([]);

    const handleUpload = (e) => {
        e.preventDefault()
        const file = e.target.files;
        setFiles([file]);
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        const dataForm = new FormData();

        for (let i = 0; i < files[0].length; i++) {
            dataForm.append("uploadFiles", files[0].item(i));
        }

        axios({
            method: 'post',
            url: API_BASE_URL + '/upload',
            data: dataForm,
            dataType: 'json'
        })
            .then(function (response) {
                for (let t = 0; t < response.data.length; t++) {
                    let url= response.data[t]['imageURL'];
                    getImage(url);
                }
            })
            .catch(function (error) {
                console.log(error);
             });

    }

    const getImage = (arr) =>{
        axios({
            method: 'get',
            url: API_BASE_URL + '/display?fileName='+arr,
            dataType: 'json'
        })
            .then(function (response) {
                const divArea = getElementsById()
                <Image name={response.data}/>
            })
            .catch(function (error) {
                console.log(error);
            });
        // console.log(images[0]);
    }
    return (
        <div>
            <input name="uploadFiles" type="file" multiple onChange={handleUpload}/>
            <button className={"uploadBtn"} onClick={handleSubmit}>
                Upload
            </button>
            <div className={uploadResults}></div>
        </div>
    );
}

export default App;
