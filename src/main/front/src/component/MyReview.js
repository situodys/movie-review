import Typography from "@mui/material/Typography";
import {Card, CardContent} from "@mui/material";
import MyModal from "./MyModal";
import {useState} from "react";


export default function MyReview(props) {

    const [showModal, setShowModal] = useState(false);

    function handleShowModal() {
        console.log(showModal);
        setShowModal(true);
    }

    const handleCloseModal= () =>{
        setShowModal(false);
    }

    return (
        props.review && <Card>
            <CardContent onClick={()=>{handleShowModal();}}>
                <Typography variant="h5" component="div">
                    {props.review.text}
                </Typography>
                <Typography sx={{mb: 1.5}} color="text.secondary">
                    {props.review.nickname}
                </Typography>
                <Typography variant="body2">
                    {props.review.regDate}
                    <div> grade: {props.review.grade}</div>
                </Typography>
            </CardContent>
            <MyModal mno={props.review.movieNo} handleClose={handleCloseModal} open={showModal}
                     review={props.review} isDataChange={props.isDataChange} dataSync={props.dataSync}/>
        </Card>


    );
};