import {Button, Container, TextField, Typography} from "@mui/material";

import axios from "axios";
import {API_BASE_URL} from "../config/config";
import NavigationBar from "../component/NavigationBar";
import UploadedImages from "../component/UploadedImages";
import {useEffect, useRef, useState} from "react";

const AddMovie = () => {

    const [files, setFiles] = useState([]);
    const [images, setImages] = useState([]);
    const [title, setTitle] = useState("");
    const removeImages = (imageURL) => {
        setImages(images.filter(image => {
            return image.thumbnailURL !== imageURL;
        }))
    }
    const mounted = useRef(false);

    useEffect(() => {
            if (!mounted.current) {
                mounted.current = true;
            } else {
                getImageURLS();
            }
        }
        , [files]);

    const handleUpload = (e) => {
        e.preventDefault()
        const file = e.target.files;
        console.log(file);
        setFiles(file);
    }

    function getImageURLS() {
        const dataForm = new FormData();
        if (!files)
            return;

        for (let i = 0; i < files.length; i++) {
            dataForm.append("uploadFiles", files.item(i));
        }

        console.log(dataForm);

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

    const handleSubmit = (e) => {
        e.preventDefault();

        const submitForm = new FormData();
        let formData={};
        let movieImageDTOList=[];
        formData.title=title;

        // submitForm.append("title", title);

        images.map((image, idx) => {
            // submitForm.append("movieImageDTOList", image);
            movieImageDTOList.push(image);
        })

        formData.movieImageDTOList = movieImageDTOList;
        console.log(formData);

        axios({
            method: 'post',
            url: API_BASE_URL + '/api/movie/add',
            data: formData,
            dataType: 'json'
        })
            .then(function (response) {
                console.log(response.data);
                window.location.href = "/";
            })
            .catch(function (error) {
                console.log(error);
            });


    }
    const handleTypedTitle = (e) => {
        e.preventDefault();
        setTitle(e.target.value)
        console.log(title);
    }
    return (
        <Container maxWidth={"md"} background={"#f5f5f5"}>
            <Typography variant={"h2"} color={"inherit"} paddingTop={5} paddingBottom={5}>Movie Register
                Page</Typography>
            <Typography variant={"h5"}>Title</Typography>
            <TextField sx={{paddingBottom: 4}} id={"outlined-basic title"} fullWidth placeholder={"Enter Title"}
                       onInput={handleTypedTitle}></TextField>
            <Button variant="contained" component="label">
                select images
                <input hidden accept="image/*" multiple type="file" onChange={handleUpload} on/>
            </Button>
            <UploadedImages images={images} removeImage={removeImages}/>
            <Button variant={"contained"} component={"label"}>
                <input hidden onClick={handleSubmit}/>
                save
            </Button>
        </Container>

    );
}

export default AddMovie