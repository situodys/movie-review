import {API_BASE_URL} from "../config/config";
import {Card, CardHeader, CardMedia, IconButton} from "@mui/material";
import ClearIcon from '@mui/icons-material/Clear';

function Image(props){
    // console.log(API_BASE_URL+'/display?fileName='+props.url);
    return <img style={{height: 200, width:200}} src={API_BASE_URL+'/display?fileName='+props.url}/>
        // <Card sx={{maxWidth: 200}}>
        //     <CardHeader
        //         action={
        //             <IconButton>
        //                 <ClearIcon></ClearIcon>
        //             </IconButton>
        //         }
        //         title={`${props.url}`}
        //     />
        //     <CardMedia/>
        // </Card>





}

export default Image;