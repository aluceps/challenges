package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"io/ioutil"

	"github.com/omise/omise-go"
	"github.com/omise/omise-go/operations"
)

type TamboonHandler struct {
	client *omise.Client
}

func (handler *TamboonHandler) ServeHTTP(resp http.ResponseWriter, req *http.Request) {
	method, path := req.Method, req.URL.Path
	fmt.Printf("%s %s\n", method, path)

	if method == "GET" && path == "/charities" {
		handler.GET(resp, req)

	} else if method == "POST" && path == "/donations" {
		handler.POST(resp, req)

	} else {
		http.NotFound(resp, req)

	}
}

func (handler *TamboonHandler) GET(resp http.ResponseWriter, req *http.Request) {
	if e := json.NewEncoder(resp).Encode(charities); e != nil {
		http.Error(resp, e.Error(), 500)
		return
	}
}

func (handler *TamboonHandler) POST(resp http.ResponseWriter, req *http.Request) {
	donation := &Donation{}
	defer req.Body.Close()

	b, err := ioutil.ReadAll(req.Body)
	if err != nil {
		fmt.Println("io error")
		return
	}

	jsonBytes := ([]byte)(b)
	data := new(Donation)
	if err := json.Unmarshal(jsonBytes, data); err != nil {
		fmt.Println("JSON Unmarshal error:", err)
		return
	}

	donation.Name = data.Name
	donation.Token = data.Token
	donation.Amount = data.Amount

	fmt.Println("step:1")
	if e := json.NewDecoder(req.Body).Decode(donation); e != nil {
		fmt.Println("step:2")
		http.Error(resp, e.Error(), 400)
		return
	}

	fmt.Println("step:3")
	charge, operation := &omise.Charge{}, &operations.CreateCharge{
		Card:        donation.Token,
		Amount:      donation.Amount,
		Currency:    "THB",
		Description: donation.Name,
	}
	fmt.Println("step:4")
	if e := handler.client.Do(charge, operation); e != nil {
		fmt.Println("step:5")
		http.Error(resp, e.Error(), 400)
		return
	}

	fmt.Println("step:6")
	if e := json.NewEncoder(resp).Encode(&Result{Success: true}); e != nil {
		fmt.Println("step:7")
		http.Error(resp, e.Error(), 500)
		return
	}
}
