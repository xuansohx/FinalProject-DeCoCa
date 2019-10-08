## 자율 주행 기반 커넥티드카 셰어링 서비스 선행 개발 :car:

> T4IR 2조 : 강용욱 백승엽 안소현 이경헌 조민경 황성민

[View Proposal](https://github.com/xuansohx/FinalProject/blob/master/proposal.pdf)

<br>

- git 에 `push` 할 때 주의할 점

:heavy_check_mark: git bash 접속해서 `git pull`을 먼저하기

:heavy_check_mark: `master` 권한으로 올리지 않기 → `branch` 생성해서 `push` 하기

:heavy_check_mark: `branch` 이름은 자신이 맡은 역할을 나타낼 수 있도록 만듦 

> 오늘 calendar 작업을 했으면, branch 이름은 `calendar`

> `commit message`에 날짜와 함께 작업 내용 상세하게 기록해주세요 :smile:

```
git branch 브랜치명 → branch 생성
git branch → 현재 접속 된 branch 확인
git checkout 브랜치명 → 해당하는 branch로 접속

git push origin 브랜치명
→ 접속된 `branch`로 push 하는 법 (저장소 이름인 'origin' 뒤에 branch 이름 써서 push 하기)
```

