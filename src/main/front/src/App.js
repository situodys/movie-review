import {useState} from "react";
import axios from "axios";
import {API_BASE_URL} from "./config/config";
import ImageList from "./ImageList";

function App() {

    // function showImages(arr) {
    //
    //     for(let i=0; i<arr.length;i++){
    //         <Image
    //     }
    // }

    const [files, setFiles] = useState([]);
    const [images, setImages] = useState([]);
    const data =[];

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
                console.log(response.data);
                for(let i=0;i<response.data.length;i++)
                setImages([...images, response.data[i].imageURL]);
            })
            .catch(function (error) {
                console.log(error);
             });
    }

    // const getImage = (arr) =>{
    //     axios({
    //         method: 'get',
    //         url: API_BASE_URL + '/display?fileName='+arr,
    //         responseType: 'blob'
    //     })
    //         .then(function (response) {
    //             console.log(response)
    //             setImages([...images,response.data])
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    // }
    return (
        <div>
            <input name="uploadFiles" type="file" multiple onChange={handleUpload}/>
            <button className={"uploadBtn"} onClick={handleSubmit}>
                Upload
            </button>
            <div class ="uploadResult"></div>
            <ImageList data={images}/>
        </div>
    );
}

export default App;
