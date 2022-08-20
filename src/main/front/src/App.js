import {useState} from "react";
import axios from "axios";
import {API_BASE_URL} from "./config/config";
import ImageList from "./ImageList";
import Image from "./Image";

function App() {

    const [files, setFiles] = useState([]);
    const [images, setImages] = useState([]);

    const handleUpload = (e) => {
        e.preventDefault()
        const file = e.target.files;
        console.log(file);
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
                console.log(response.data);
                setImages(response.data);
            })
            .catch(function (error) {
                console.log(error);
             });
    }

    return (
        <div>
            <input name="uploadFiles" type="file" multiple onChange={handleUpload}/>
            <button className={"uploadBtn"} onClick={handleSubmit}>
                Upload
            </button>
            <div class ="uploadResult"></div>
            {
                images&& images.map(
                    item=>{ return <Image url={item.thumbnailURL}/>}
                )
            }
        </div>
    );
}

export default App;
