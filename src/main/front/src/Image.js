import {API_BASE_URL} from "./config/config";

function Image(props){
    console.log(API_BASE_URL+'/display?fileName='+props.url);
    return <img src={API_BASE_URL+'/display?fileName='+props.url}/>
}

export default Image;