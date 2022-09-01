import {API_BASE_URL} from "../config/config";
import {Card, CardHeader, CardMedia, IconButton} from "@mui/material";
import ClearIcon from '@mui/icons-material/Clear';
import axios from "axios";
import {useEffect, useState} from "react";

function Image(props) {
    console.log(API_BASE_URL + '/display?fileName=' + props.imageURL);
    console.log(props.imageURL);

    const [thumbnail, setThumbnail] = useState(props.thumbnailURL);

    const handleRemoveIcon = () => {

        const dataForm = new FormData();
        dataForm.append("fileName", props.imageURL);

        axios({
            method: 'post',
            url: API_BASE_URL + '/remove',
            dataType: 'json',
            data: dataForm
        })
            .then(function (response) {
                console.log(response);
                if (response.data === true) {
                    props.removeImage(thumbnail)
                }

            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <div>
            {!props.flag &&<ClearIcon onClick={handleRemoveIcon}/>}
            <img style={{height: 200, width: 200, paddingTop: 65, borderRadius: '16px'}}
                 src={API_BASE_URL + '/display?fileName=' + thumbnail}/>
        </div>
    )
}

export default Image;