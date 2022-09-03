import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {TextField} from "@mui/material";
import {useEffect, useState} from "react";
import {Comment} from "@mui/icons-material";
import axios from "axios";
import {API_BASE_URL} from "../config/config";

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export default function MyModal(props) {
    const [open, setOpen] = useState(false);
    const [isExist, setIsExist] = useState(true);
    const [reviewerId, setReviewerId] = useState("");
    const [grade, setGrade] = useState("");
    const [text, setText] = useState("");
    const [stateCheck, setStateCheck] = useState(props.dataSync);

    useEffect(() => {
        if (!open) {
            props.handleClose();
        }
        if (props.open) {
            // console.log(open);
            setOpen(true);
        }
        if (props.review !== undefined) {
            if (reviewerId == "" && grade == "" && text == "") {
                // || reviewerId != props.review.memberId
                setReviewerId(props.review.memberId);
                setGrade(props.review.grade);
                setText(props.review.text);
            }
            // }
            // if (grade == "") {
            //     //|| grade != props.review.grade
            //     setGrade(props.review.grade);
            // }
            // if (text == "" ) {
            //     //|| text != props.review.text
            //     setText(props.review.text);
            // }

        } else {
            setIsExist(false);
        }
    });

    const handleClose = () => {
        setOpen(false);
        if (isExist) {
            setText(props.review.text);
            setGrade(props.review.grade);
            setReviewerId(props.review.memberId);
        }
    }
    const handleAfterSave = () => {
        setText("");
        setGrade("");
        setReviewerId("");
    }


    function handleReviewerId(e) {
        e.preventDefault();
        console.log(reviewerId)
        setReviewerId(e.target.value);
    }

    function handleGrade(e) {
        e.preventDefault();
        console.log(grade);
        setGrade(e.target.value);
    }

    function handleText(e) {
        e.preventDefault();
        console.log(text);
        setText(e.target.value);
    }

    function handleSave(e) {
        let data = {};
        data.memberId = reviewerId;
        data.text = text;
        data.grade = grade;
        data.movieNo = props.mno;

        axios({
            method: 'post',
            url: API_BASE_URL + `/api/review/${props.mno}`,
            data: data,
            dataType: 'json'
        })
            .then(function (response) {
                console.log(response.data);
                handleClose();
                handleAfterSave();
                props.isDataChange();
                // props.handleClose(false);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    function handleModify(e) {
        let data = {};
        data.memberId = reviewerId;
        data.text = text;
        data.grade = grade;
        data.movieNo = props.mno;
        data.reviewNo = props.review.reviewNo;

        axios({
            method: 'put',
            url: API_BASE_URL + `/api/review/${props.mno}/${props.review.reviewNo}`,
            data: data,
            dataType: 'json'
        })
            .then(function (response) {
                // console.log(response.data);
                setOpen(false);
                setGrade(grade);
                setText(text);
                setReviewerId(reviewerId);
                props.isDataChange();
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    function handleRemove() {
        axios({
            method: 'delete',
            url: API_BASE_URL + `/api/review/${props.mno}/${props.review.reviewNo}`,
            dataType: 'json'
        })
            .then(function (response) {
                console.log(response.data);
                setOpen(false);
                props.isDataChange();
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <div>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Movie Review
                    </Typography>

                    <Typography id="modal-modal-description" sx={{mt: 2}}>Reviewer ID</Typography>
                    <TextField sx={{paddingBottom: 4}} id="modal-modal-description" fullWidth placeholder={"Enter ID"}
                               onChange={handleReviewerId} value={reviewerId}></TextField>

                    <Typography id="modal-modal-description" sx={{mt: 2}}>Grade</Typography>
                    <TextField sx={{paddingBottom: 4}} id="modal-modal-description" fullWidth

                               onChange={handleGrade} value={grade}></TextField>

                    <Typography id="modal-modal-description" sx={{mt: 2}}>Review Text</Typography>
                    <TextField sx={{paddingBottom: 4}} id="modal-modal-description" fullWidth
                               onChange={handleText} value={text}></TextField>
                    {
                        isExist && <Button style={{float: "right"}} variant={"contained"} size={"small"}
                                           onClick={handleRemove} color={"error"}>
                            Remove
                        </Button>
                    }
                    {
                        isExist && <Button style={{float: "right"}} variant={"contained"} size={"small"}
                                           onClick={handleModify} color={"warning"}>
                            Modify
                        </Button>
                    }

                    <Button style={{float: "right"}} variant={"contained"} size={"small"}
                            onClick={handleClose}>
                        close
                    </Button>
                    {!isExist && <Button variant={"contained"} size={"small"} onClick={handleSave}>
                        save
                    </Button>}
                </Box>
            </Modal>
        </div>
    );
}
