import {Paper, Stack} from "@mui/material";
import Image from "./Image";
import MyReview from "./MyReview";
import MyModal from "./MyModal";

export default function Reviews(props) {

    return (
        <Paper style={
            {
                background: "aliceblue",
                alignContent: "center",
                overflow: "auto"
            }
        }>
            <Stack spacing={2} justifyContent={"center"} alignContent={"center"} overflow={"auto"}>
                {props.reviews && props.reviews.map((item, idx) => (
                    <MyReview id={idx} review={item} isDataChange={props.isDataChange} dataSync={props.dataSync}/>
                    ))}
            </Stack>
        </Paper>
    );
};